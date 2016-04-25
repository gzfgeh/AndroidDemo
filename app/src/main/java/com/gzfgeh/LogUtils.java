package com.gzfgeh;

import android.util.Log;

/**
 * Created by guzhenf on 7/20/2015.
 */
public class LogUtils {
    public static String TAG = "TAG";

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int LEVEL;

    public static void v(String msg){
        if (LEVEL <= VERBOSE)
            Log.v(TAG, msg);
    }

    public static void d(String msg){
        if (LEVEL <= VERBOSE)
            Log.d(TAG, msg);
    }

    public static void i(String msg){
        if (LEVEL <= VERBOSE)
            Log.i(TAG, msg);
    }

    public static void w(String msg){
        if (LEVEL <= VERBOSE)
            Log.w(TAG, msg);
    }

    public static void e(String msg){
        if (LEVEL <= VERBOSE)
            Log.e(TAG, msg);
    }

}
