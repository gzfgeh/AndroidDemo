package com.gzfgeh;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;


/**
 * Description: 初始化一些全局类
 * Created by guzhenfu on 2016/2/25 15:03.
 */
public class APP extends Application {
    private static Context context;

    public static Context getContext(){
        return context;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();


    }

    private static boolean debugMode(){
        ApplicationInfo info = getContext().getApplicationInfo();
        if ((info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            return true;
        }else{
            return false;
        }
    }

}
