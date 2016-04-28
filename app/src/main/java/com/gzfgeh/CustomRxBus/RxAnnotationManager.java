package com.gzfgeh.CustomRxBus;

import com.gzfgeh.CustomRxBus.annotation.Accept;
import com.gzfgeh.CustomRxBus.annotation.AcceptScheduler;
import com.gzfgeh.CustomRxBus.annotation.AcceptType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 注解器
 */
public class RxAnnotationManager {
    private static final String TAG = RxAnnotationManager.class.getName();
    private Object object;
    private static Map<Object, RxAnnotationManager> managerMap;

    public RxAnnotationManager(Object object) {
        this.object = object;
    }

    public static void bind(Object object) {
        if (null == managerMap) {
            managerMap = new HashMap<>();
        }
        // 如果已经绑定 直接返回
        if (null != managerMap.get(object)) {
            return;
        }
        // 绑定RxBus
        RxAnnotationManager manager = new RxAnnotationManager(object);
        manager.bindMethods(object.getClass());
        // 添加到注解解析器队列
        managerMap.put(object, manager);
    }


    private void bindMethods(Class clazz) {
        // 获取所有私有级别>private的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 如果有accept注解
            if (method.isAnnotationPresent(Accept.class)) {
                try {
                    // 绑定观察者...
                    parserObservableEventAnnotations(method);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private List<ObservableWrapper> registeredObservable;

    public <T> void parserObservableEventAnnotations(Method method) throws Exception {
        if (null == method || !method.isAnnotationPresent(Accept.class)) {
            return;
        }
        // 获取参数
        Class[] params = method.getParameterTypes();
        // 参数必须是两个，第1个必须是Object类型的tag
        if (null == params || 2 != params.length || !Object.class.isAssignableFrom(params[0])) {
            throw new Exception("the method[" + method.getName() + "] must defined xxx(Object tag, T object)");
        }

        Accept accept = method.getAnnotation(Accept.class);
        AcceptType[] acceptTypes = accept.value();

        // 默认clazz参数类型
        Class<T> targetClazz = params[1];
        // 默认clazz参数类型的全类名
        String targetTag = targetClazz.getName();
        Class<T> specClazz;
        String specTag;
        int acceptTypeLength = null == acceptTypes ? 0 : acceptTypes.length;
        switch (acceptTypeLength) {
            case 0: // 如果acceptType是空，则说明具体的类型是params[1]，所以params[1]不能为Object类型
                if (Object.class.equals(targetClazz)) {
                    throw new Exception("the method[" + method.getName() + "] must defined xxx(Object tag, T object)");
                }
                registerObservable(method, targetTag, targetClazz, accept.acceptScheduler());
                break;
            case 1: // 如果只有一个，且acceptType中tag不为空，则使用acceptType中的tag

                // 默认clazz参数类型，acceptType中指定clazz优先
                specClazz = acceptTypes[0].clazz();

                if (!Object.class.equals(specClazz)) {
                    targetClazz = specClazz;
                }
                // TODO 如果目标参数类型是 object类型 则需要具体指定类型
                if (Object.class.equals(targetClazz)) {
                    throw new Exception("the method[" + method.getName() + "] must defined xxx(Object tag, T object) OR clazz of @AcceptType");
                }
                // 默认tag参数类型的全类名
                targetTag = targetClazz.getName();
                // acceptType中指定tag优先
                specTag = acceptTypes[0].tag();

                // 有值
                if ("".equals(specTag)) {
                    specTag = targetTag;
                }
                registerObservable(method, specTag, targetClazz, accept.acceptScheduler());
                break;
            default: // 如果有多个，则params[1]必须是Object
                if (!Object.class.equals(targetClazz)) {
                    throw new Exception("the method[" + method.getName() + "] must defined xxx(Object tag, Object object)");
                }
                for (AcceptType acceptType : acceptTypes) {
                    specClazz = acceptType.clazz();
                    specTag = acceptType.tag();
                    // 默认tag参数类型的全名，acceptType中指定tag优先
                    registerObservable(method, "".equals(specTag) ? specClazz.getName() : specTag, specClazz, accept.acceptScheduler());
                }
                break;
        }


    }

    private <T> void registerObservable(final Method method, final String tag, Class<T> clazz, AcceptScheduler acceptScheduler) {
        if (null == tag || null == clazz) {
            return;
        }
        // 判断是否为null
        if (null == registeredObservable) {
            registeredObservable = new ArrayList<>();
        }

        Observable<T> observable = RxBus.get().register(tag, clazz);
        registeredObservable.add(new ObservableWrapper(tag, observable));

        Observable<T> schedulerObservable;
        Scheduler scheduler;
        switch (acceptScheduler) {
            case NEW_THREAD:
                scheduler = Schedulers.newThread();
                break;
            case IO:
                scheduler = Schedulers.io();
                break;
            case IMMEDIATE:
                scheduler = Schedulers.immediate();
                break;
            case COMPUTATION:
                scheduler = Schedulers.computation();
                break;
            case TRAMPOLINE:
                scheduler = Schedulers.trampoline();
                break;
//            case EXECUTOR:
//                Executor executor = DefaultAcceptConfiguration.getInstance().applyAcceptExecutor();
//                if (null == executor) {
//                    throw new RuntimeException("DefaultAcceptConfiguration applyAcceptExecutor() return null, please register OnDefaultAcceptConfiguration in Application");
//                }
//                schedulerObservable = observable.observeOn(Schedulers.from(executor));
//                break;
//            case HANDLER:
//                Handler handler = DefaultAcceptConfiguration.getInstance().applyAcceptHandler();
//                if (null == handler) {
//                    throw new RuntimeException("DefaultAcceptConfiguration applyAcceptHandler() return null, please register OnDefaultAcceptConfiguration in Application");
//                }
//                schedulerObservable = observable.observeOn(AndroidSchedulers.handlerThread(handler));
//                break;
            default: // MAIN_THREAD default
                scheduler = AndroidSchedulers.mainThread();
                break;
        }
        // 绑定事件
        // 这里的observable与registered的是一个
        schedulerObservable = observable.observeOn(scheduler);
        schedulerObservable.subscribe(t -> {
            try {
                // 调用方法
                method.invoke(object, tag, t);
            } catch (Exception e) {
            }
        });


    }

    public void clear() {
        if (null != registeredObservable && !registeredObservable.isEmpty()) {
            // 解除所有绑定
            for (ObservableWrapper observableWrapper : registeredObservable) {
                RxBus.get().unregister(observableWrapper.getTag(), observableWrapper.getObservable());
            }
        }
    }

    public static void unBind(Object object) {
        RxAnnotationManager manager = managerMap.get(object);
        if (null != manager) {
            managerMap.remove(object);
            manager.clear();
            manager = null;
        }
    }


}
