package com.gzfgeh.java.Proxy;

/**
 * Description:客户端
 */

public class TestMain {
    public static void main(String[] args) {
//        RetrofitProxy proxy = new RetrofitProxy(new Retrofit());
        RetrofitInterface retrofitInterface =  RetrofitProxy.create(RetrofitInterface.class);
        retrofitInterface.retrofitInterfaceFunc("12345");
    }
}
