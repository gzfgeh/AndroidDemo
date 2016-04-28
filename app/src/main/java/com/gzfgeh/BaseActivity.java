package com.gzfgeh;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

import com.gzfgeh.CustomRxBus.RxAnnotationManager;
import com.gzfgeh.CustomRxBus.annotation.Accept;

/**
 * Description:
 * Created by guzhenfu on 2016/4/27 17:27.
 */
public class BaseActivity extends AppCompatActivity {
    protected View rootView;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        RxAnnotationManager.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setRootView(bundle);
    }

    protected void setRootView(Bundle savedInstanceState){
        if (savedInstanceState == null){
            rootView = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
            if (rootView != null) {
                rootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        rootView.getViewTreeObserver().removeOnPreDrawListener(this);
                        startRootAnimation();
                        return true;
                    }
                });
            }
        }
    }

    private void startRootAnimation() {
        rootView.setScaleY(0.1f);
        rootView.setScaleX(0.1f);
        rootView.setPivotY(rootView.getY() + rootView.getHeight() / 2);

        rootView.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }

    @Accept
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxAnnotationManager.unBind(this);
    }
}
