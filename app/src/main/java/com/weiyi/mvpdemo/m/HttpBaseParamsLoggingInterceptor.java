package com.weiyi.mvpdemo.m;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

class HttpBaseParamsLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        Request.Builder requestBuilder = request.newBuilder();
//        RequestBody formBody = new FormBody.Builder()
//                .add("userId", "10000")
//                .add("sessionToken", "E34343RDFDRGRT43RFERGFRE")
//                .add("q_version", "1.1")
//                .add("device_id", "android-344365")
//                .add("device_os", "android")
//                .add("device_osversion","6.0")
//                .add("req_timestamp", System.currentTimeMillis() + "")
//                .add("app_name","forums")
//                .add("sign", "md5")
//                .build();

//        String postBodyString = request.body().toString();
//        postBodyString += ((postBodyString.length() > 0) ? "&" : "") +  formBody.toString();
//        request = requestBuilder
//                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"),
//                        postBodyString))
//                .build();
        return chain.proceed(request);
    }
}
