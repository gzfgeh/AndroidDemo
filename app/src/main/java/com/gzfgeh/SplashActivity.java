package com.gzfgeh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

//        Glide.with(SplashActivity.this)
//                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
//                .into(startPage);
//



        Observable.mergeDelayError(
                getBitmap(),
                Observable.timer(2, TimeUnit.SECONDS).map(c->null))
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

//                        tempPage.buildDrawingCache();
//                        tempPage.setDrawingCacheEnabled(true);
//                        if (tempPage.getDrawingCache() == null)
//                            tempPage.setVisibility(View.GONE);
//                        else
//                            startPage.setVisibility(View.GONE);
//                        tempPage.setDrawingCacheEnabled(false);

                        startPage.setVisibility(View.GONE);
                        System.out.println("----Sequence complete.");
                        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                        .map(l -> {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                            return null;
                        }).subscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("----Error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Object aLong) {

                        System.out.println("----Next:" + aLong);
                    }
                });



    }


    private Observable<Object> getBitmap(){
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                Glide.with(SplashActivity.this)
                        .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                        .into(tempPage);
                System.out.println("----getBitmap:");

                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        }).subscribeOn(AndroidSchedulers.mainThread());

    }

}



