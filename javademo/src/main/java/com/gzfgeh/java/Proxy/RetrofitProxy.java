package com.gzfgeh.java.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: 代理类
 */
public class RetrofitProxy {
//    private Retrofit retrofit;
//
//    public RetrofitProxy(Retrofit retrofit) {
//        this.retrofit = retrofit;
//    }

    public static <T> T create(Class<T> service){
        if (!service.isInterface()){
            throw new IllegalArgumentException("API declarations must be interfaces. ");
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before\n");
//                        method.invoke(retrofit, args);
                        System.out.printf("function name: " + method.getName() + "\n");
                        System.out.printf("function return type: " + method.getReturnType().getName() + "\n");
                        System.out.printf("args name: " + args[0].toString() + "\n");
                        System.out.printf("after \n");
                        return null;
                    }
                });
    }
}
