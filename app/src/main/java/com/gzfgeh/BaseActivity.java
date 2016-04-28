package com.gzfgeh;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gzfgeh.CustomRxBus.RxAnnotationManager;
import com.gzfgeh.CustomRxBus.annotation.Accept;

/**
 * Description:
 * Created by guzhenfu on 2016/4/27 17:27.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxAnnotationManager.bind(this);
    }

    @Accept
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxAnnotationManager.unBind(this);
    }
}
