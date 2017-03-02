package com.gzfgeh.java.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 * Created by GUZHENFU on 2017/3/2 18:14.
 */

public class RetrofitProxy {
    private Retrofit retrofit;

    public RetrofitProxy(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public <T> T create(Class<T> service){
        if (!service.isInterface()){
            throw new IllegalArgumentException("API declarations must be interfaces. ");
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(" before\n");
                        method.invoke(retrofit, args);
                        System.out.printf("after \n");
                        return null;
                    }
                });
    }
}
