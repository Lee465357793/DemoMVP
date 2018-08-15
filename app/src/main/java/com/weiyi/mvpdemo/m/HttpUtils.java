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
                .client(getOkHttpClient())
                .build();
    }

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor  interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zgx", "OkHttp====message " + message);
            }
        });
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new HttpBaseParamsLoggingInterceptor())
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(5000, TimeUnit.MILLISECONDS) //超时时间
                .build();
    }
}
