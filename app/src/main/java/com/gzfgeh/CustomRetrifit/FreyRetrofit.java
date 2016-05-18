package com.gzfgeh.CustomRetrifit;

import org.xml.sax.helpers.XMLReaderFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Description:
 * Created by guzhenfu on 2016/2/29 17:21.
 */
@Singleton
public class FreyRetrofit {
    private static FreyRetrofit freyRetrofit;
    private FreyService freyService;

    public static FreyRetrofit getInstance(){
        if (freyRetrofit == null){
            synchronized (FreyRetrofit.class){
                if (freyRetrofit == null)
                    freyRetrofit = new FreyRetrofit();
            }
        }
        return freyRetrofit;
    }

    @Inject
    public FreyRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://appu.shcem.com/frey/publish/update/Android/AppVersion.xml?"+System.currentTimeMillis())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        freyService = retrofit.create(FreyService.class);
    }

    public FreyService getFreyService(){
        return freyService;
    }
}
