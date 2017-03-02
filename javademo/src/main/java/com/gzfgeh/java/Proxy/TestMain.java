package com.gzfgeh.java.Proxy;

/**
 * Description:
 * Created by GUZHENFU on 2017/3/2 18:15.
 */

public class TestMain {
    public static void main(String[] args) {
        RetrofitProxy proxy = new RetrofitProxy(new Retrofit());
        RetrofitInterface retrofitInterface =  proxy.create(RetrofitInterface.class);
        retrofitInterface.retrofitInterfaceFunc("12345");
    }
}
