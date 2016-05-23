package com.gzfgeh.CustomRetrifit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzfgeh.LogUtils;
import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Created by guzhenfu on 2016/5/17 11:44.
 */
public class RetrofitActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.retrofit)
    Button retrofit;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.behavior_subject)
    Button behavior;
    @Bind(R.id.wave_view)
    WaterWaveView waveView;
    @Bind(R.id.water_view)
    WaterView waterView;

    private CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        retrofit.setOnClickListener(this);
        behavior.setOnClickListener(this);
        waveView.setmWaterLevel(0.7f);
        waveView.startWave();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrofit: {
                Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            for (int i = 0; i < 1000; i++) {
                                LogUtils.i("onNext:----subscriber" + i % 10);
                                if (i % 10 == 0) {
                                    subscriber.onNext(i);
                                }

                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            subscriber.onCompleted();
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                text.setText("done");
                                waterView.setWaterLevel(1.0f);
                                dialog.setWaveProgress(1.0f);
                                dialog = null;
                                LogUtils.i("onNext:--onCompleted--");
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtils.i("onNext:--error--" + e.getMessage());
                            }

                            @Override
                            public void onNext(Integer o) {
                                text.setText(o + "");
                                LogUtils.i("onNext:----" + o);
                                float percent = o/1000f;
                                //waterView.setWaterLevel(percent);

                                if (dialog == null) {
                                    dialog = new CustomProgressDialog(RetrofitActivity.this);
                                    dialog.showDialog();
                                }

                                dialog.setWaveProgress(percent);
                            }
                        });
            }
            break;

            case R.id.behavior_subject: {
            }
            break;
        }
    }

}
