package com.gzfgeh.java.Proxy;

/**
 * Description:被代理类
 */
public class Retrofit implements RetrofitInterface {
    @Override
    public String retrofitInterfaceFunc(String s) {
        System.out.printf("progress " + s + "\n");
        return s;
    }
}
