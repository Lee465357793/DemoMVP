package com.weiyi.mvpdemo.utils.rxjava;

import android.util.Log;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * 封装Subscriber
 * Created by YoKey.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        Log.e("测试", "onCompleted()");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.e("测试", "onError()");
        String msg;
        if (e instanceof UnknownHostException) {
            msg = "没有网络...";
        } else if (e instanceof SocketTimeoutException) {
            // 超时
           msg = "请求超时...";
        }else{
            msg = "请求失败，请稍后重试...";
        }
        _onError(msg);
    }

    @Override
    public void onNext(T t) {
        Log.e("测试", "onNext()");
        _onNext(t);
    }

    public abstract void _onNext(T t);

    public abstract void _onError(String msg);
}
