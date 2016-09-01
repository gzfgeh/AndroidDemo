package com.gzfgeh.demo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.gzfgeh.demo.Utils.LogUtils;

public class APP extends Application {
    private static Context context;

    public static APP get(Context context) {
        return (APP) context.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    private static boolean debugMode() {
        ApplicationInfo info = getContext().getApplicationInfo();
        if ((info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (debugMode()) {
            LogUtils.LEVEL = 0;
        } else {
            LogUtils.LEVEL = LogUtils.NOTHING;
        }

    }
}