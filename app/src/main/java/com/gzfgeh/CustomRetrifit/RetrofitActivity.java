package com.gzfgeh.CustomRetrifit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzfgeh.LogUtils;
import com.gzfgeh.R;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

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

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

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
//        FreyRetrofit.getInstance().getFreyService().getXMLIS().enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, Response<Response> response) {
//                Toast.makeText(RetrofitActivity.this, response.body().message(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                Toast.makeText(RetrofitActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
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
                                    Thread.sleep(100);
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
                                //waterView.setWaterLevel(1.0f);
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
                                //waterView.setWaterLevel(o/1000);
//                                compositeSubscription.add(Observable.interval(30, TimeUnit.MILLISECONDS).subscribe(new Action1<Long>() {
//                                    @Override
//                                    public void call(Long aLong) {
//                                        waterView.setWaterLevel(o/1000);
//                                    }
//                                }));
                            }
                        });
            }
            break;

            case R.id.behavior_subject: {
                BehaviorSubject bs = BehaviorSubject.create(1);
                bs.onNext(2);
                bs.onCompleted();
                bs.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        text.setText(integer + "");
                    }
                });
            }
            break;
        }
    }

}
