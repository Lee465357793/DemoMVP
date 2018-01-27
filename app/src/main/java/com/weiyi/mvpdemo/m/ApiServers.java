package com.weiyi.mvpdemo.m;

import com.weiyi.mvpdemo.m.bean.CheckversionBean;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.Observer;

/**
 * Created by Lee on 2018/1/25 0025.
*/

public interface ApiServers {
    public static String BaseUrl = "http://api.paicl.net/api/";

    @FormUrlEncoded
    @POST("update/check-update")
    Observable<CheckversionBean> checkVersion(@Field("version") String version, @Field("client_type") String client_type, @Field("app_type") String app_type);
}
