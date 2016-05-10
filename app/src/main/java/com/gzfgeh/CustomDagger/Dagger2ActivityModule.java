package com.gzfgeh.CustomDagger;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Created by guzhenfu on 2016/5/10 11:26.
 */
@Module
public class Dagger2ActivityModule {
    private Dagger2Activity dagger2Activity;

    public Dagger2ActivityModule(Dagger2Activity dagger2Activity) {
        this.dagger2Activity = dagger2Activity;
    }

    @Provides
    @ActivityScope
    Dagger2ActivityPresenter provideDagger2ActivityPresenter(User user){
        return new Dagger2ActivityPresenter(dagger2Activity, user);
    }
}
