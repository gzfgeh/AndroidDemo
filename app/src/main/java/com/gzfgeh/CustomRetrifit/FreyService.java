package com.gzfgeh.CustomRetrifit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Description:
 * Created by guzhenfu on 2016/2/29 17:04.
 */
public interface FreyService {

    Call<retrofit2.Response> getXMLIS();

    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
