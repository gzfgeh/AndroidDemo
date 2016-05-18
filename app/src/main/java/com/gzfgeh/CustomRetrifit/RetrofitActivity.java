package com.gzfgeh.CustomRetrifit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gzfgeh.LogUtils;
import com.gzfgeh.R;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        retrofit.setOnClickListener(this);
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

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 20000; i++) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(i + "");
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        text.setText("done");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        text.setText((String) o);
                        LogUtils.i("onNext:----" + o);
                    }
                });
    }
}
