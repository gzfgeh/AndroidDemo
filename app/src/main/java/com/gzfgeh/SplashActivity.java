package com.gzfgeh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Description:
 * Created by guzhenfu on 2016/4/28 18:40.
 */
public class SplashActivity extends Activity {
    @Bind(R.id.start_page)
    ImageView startPage;
    @Bind(R.id.temp_page)
    ImageView tempPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Glide.with(SplashActivity.this)
                .load("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg")
                .placeholder(R.drawable.startpage)
                .into(tempPage);

        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onNext(Object o) {
                        startPage.setVisibility(View.GONE);
                        tempPage.setVisibility(View.VISIBLE);

                        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                                .map(aLong -> {
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                    finish();
                                    return null;
                                }).subscribe();

                    }
                });
    }

}



