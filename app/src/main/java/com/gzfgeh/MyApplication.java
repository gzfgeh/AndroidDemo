package com.gzfgeh;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.gzfgeh.CustomRxBus.accept.DefaultAcceptConfiguration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 6/15/15.
 */
public class MyApplication extends Application {
    private Executor acceptExecutor = Executors.newCachedThreadPool();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();

        DefaultAcceptConfiguration.getInstance().registerAcceptConfiguration(new DefaultAcceptConfiguration.OnDefaultAcceptConfiguration() {
            @Override
            public Executor applyAcceptExecutor() {
                return acceptExecutor;
            }

            @Override
            public Handler applyAcceptHandler() {
                return handler;
            }
        });
    }
}
