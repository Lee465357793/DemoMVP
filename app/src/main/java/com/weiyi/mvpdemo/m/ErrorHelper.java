package com.weiyi.mvpdemo.m;

import android.content.Context;

import com.weiyi.mvpdemo.utils.LogUtil;

/**
 * Created by 张 奎 on 2017-09-08 15:22.
 */

public class ErrorHelper {
    public static void onError(Context context, Throwable throwable) {
        String errorString = throwable.toString();
        LogUtil.e("aaaa==============" + throwable.toString());
        //返回的错误为空
//        if (errorString == null) {
//            ToastUtil.showShort(context.getString(R.string.generic_server_down));
////            ToastUtil.initToast("网络数据异常，请重试");
//        }
//        //请求超时
//        if (errorString.contains("TimeoutException") || errorString.contains("SocketTimeoutException")) {
//            ToastUtil.showShort(context.getString(R.string.request_time_out));
////            ToastUtil.initToast("网络请求超时，请重试");
//        }
//        //能识别的请求异常，忽略不提示
//        if (errorString.contains("SSLException")) {
//
//        }
//        //403、404等服务错误
//        if (errorString.contains("ServiceConfigurationError") || errorString.contains("AuthenticatorException")) {
//            ToastUtil.showShort(context.getString(R.string.generic_server_down));
////            ToastUtil.initToast("网络数据异常，请重试");
//        }
//        //网络未连接
//        if (errorString.contains("NetworkErrorException") || errorString.contains("NoConnectionPendingException") || errorString.contains("UnknownHostException")) {
//            ToastUtil.showShort(context.getString(R.string.network_hint));
//        }
//        //连接不到服务器
//        if (errorString.contains("ConnectException")) {
//            ToastUtil.showShort(context.getString(R.string.fail_to_connected));
////            ToastUtil.initToast("网络连接失败");
//        }
    }
}