package com.weiyi.mvpdemo;

import android.app.Application;

import com.weiyi.mvpdemo.m.HttpUtils;
import com.weiyi.mvpdemo.m.ApiServers;

/**
 * Created by Lee on 2018/1/25 0025.
 */

public class MyAppLication extends Application{

    public static MyAppLication getApplication() {
        return mApplication;
    }

    private static MyAppLication mApplication;

    public ApiServers getApiServers() {
        return mApiServers;
    }

    private ApiServers mApiServers;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mApiServers = HttpUtils.getInstence();//初始化Retrofit

    }
}
