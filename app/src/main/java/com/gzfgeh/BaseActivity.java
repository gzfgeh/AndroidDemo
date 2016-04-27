package com.gzfgeh;

import android.app.Activity;

import com.gzfgeh.CustomRxBus.RxBusAnnotationManager;
import com.gzfgeh.CustomRxBus.accept.Accept;
import com.wangjie.androidinject.annotation.present.AIAppCompatActivity;

import java.lang.reflect.Method;

/**
 * Description:
 * Created by guzhenfu on 2016/4/27 17:27.
 */
public class BaseActivity extends AIAppCompatActivity {
    private RxBusAnnotationManager rxBusAnnotationManager;

    @Override
    public void parserMethodAnnotations(Method method) throws Exception {
        if (method.isAnnotationPresent(Accept.class)) {
            if (null == rxBusAnnotationManager) {
                rxBusAnnotationManager = new RxBusAnnotationManager(this);
            }
            rxBusAnnotationManager.parserObservableEventAnnotations(method);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != rxBusAnnotationManager) {
            rxBusAnnotationManager.clear();
        }
    }
}
