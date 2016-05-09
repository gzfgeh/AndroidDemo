package com.gzfgeh.CustomDagger;

import android.content.Context;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Created by guzhenfu on 16/5/9.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
@Module
public class HomeModle {
    private static final int COUNT = 1000;

    private final Context context;


    public HomeModle(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    Context provideActivityContext() {
        return context;
    }

    @Provides
    @ActivityScope
    List<String> provideUsers() {
        List<String> users = new ArrayList<>(COUNT);

        for (int i = 0; i < COUNT; i++) {
            users.add("item " + i);
        }

        return users;
    }

}
