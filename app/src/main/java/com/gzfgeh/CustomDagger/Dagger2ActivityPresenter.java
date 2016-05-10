package com.gzfgeh.CustomDagger;

import javax.inject.Inject;

/**
 * Description:
 * Created by guzhenfu on 2016/5/10 09:51.
 */
public class Dagger2ActivityPresenter {
    private Dagger2Activity activity;

    @Inject
    public Dagger2ActivityPresenter(Dagger2Activity activity) {
        this.activity = activity;
    }

    public void showText(){
        activity.setTextView("----");
    }
}
