package com.gzfgeh.CustomDagger;

import dagger.Component;

/**
 * Description:
 * Created by guzhenfu on 16/5/9.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
@ActivityScope
@Component(modules = {HomeModle.class, Dagger2ActivityModule.class})
public interface UserComponent {
    void inject(Dagger2Activity activity);

}
