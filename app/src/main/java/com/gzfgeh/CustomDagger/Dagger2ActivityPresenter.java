package com.gzfgeh.CustomDagger;

import javax.inject.Inject;

/**
 * Description:
 * Created by guzhenfu on 2016/5/10 09:51.
 */
public class Dagger2ActivityPresenter {
    private Dagger2Activity activity;
    private User user;

    @Inject
    public Dagger2ActivityPresenter(Dagger2Activity activity, User user) {
        this.activity = activity;
        this.user = user;
    }

    public void showText(){
        activity.setTextView(user.getName());
    }
}
