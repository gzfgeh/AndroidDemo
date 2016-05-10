package com.gzfgeh.CustomDagger;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Created by guzhenfu on 2016/5/10 13:25.
 */
@Module
public class UserModule {

    @Provides
    User provideUser(){
        User user = new User();
        user.setId("1");
        user.setName("dagger user");
        return user;
    }
}
