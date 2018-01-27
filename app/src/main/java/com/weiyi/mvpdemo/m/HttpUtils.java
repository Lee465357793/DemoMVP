package com.weiyi.mvpdemo.m;

import com.weiyi.mvpdemo.utils.RxConverterFactoryUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lee on 2018/1/25 0025.
 */

public class HttpUtils {

    public static ApiServers getInstence(){
        return initRetrofit().create(ApiServers.class);
    }

    public static Retrofit initRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(ApiServers.BaseUrl) //设置 域名
                .addConverterFactory(RxConverterFactoryUtil.create()) //设置GSON 解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //设置支持RxJAVA
                .build();
    }
}
