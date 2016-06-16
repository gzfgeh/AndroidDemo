package com.gzfgeh.CurveView;

import android.os.Bundle;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.LogUtils;
import com.gzfgeh.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Created by guzhenfu on 2016/6/3 14:09.
 */
public class CurveViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve_view);
    }
}
