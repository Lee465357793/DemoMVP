package com.weiyi.mvpdemo.m;

import android.util.Log;

import com.weiyi.mvpdemo.utils.rxjava.RxConverterFactoryUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

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

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor  interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zgx", "OkHttp====message " + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(5000, TimeUnit.MICROSECONDS)
                .writeTimeout(5000, TimeUnit.MICROSECONDS)
                .addInterceptor(new HttpBaseParamsLoggingInterceptor())
                .build();
    }
}
