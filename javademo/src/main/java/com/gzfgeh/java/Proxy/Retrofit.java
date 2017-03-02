package com.gzfgeh.java.Proxy;

/**
 * Description:
 * Created by GUZHENFU on 2017/3/2 19:45.
 */

public class Retrofit implements RetrofitInterface {
    @Override
    public void retrofitInterfaceFunc(String s) {
        System.out.printf("progress " + s + "\n");
    }
}
