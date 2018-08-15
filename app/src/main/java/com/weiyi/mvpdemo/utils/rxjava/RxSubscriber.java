package com.weiyi.mvpdemo.utils.rxjava;

import android.util.Log;

import com.weiyi.mvpdemo.p.base.BasePresenter;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * 封装Subscriber
 * Created by YoKey.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    private BasePresenter mBasePresenter = null;


    public RxSubscriber(BasePresenter basePresenter) {
        this.mBasePresenter = basePresenter;
    }

    public RxSubscriber() {

    }


//    @Override
//    public void onCompleted() {
//        Log.e("测试", "onCompleted()");
//    }


    @Override
    public void onStart() {
        super.onStart();
        if (mBasePresenter != null){
            mBasePresenter.start();
        }
    }


    @Override
    public void onCompleted() {
        if (mBasePresenter != null){
            mBasePresenter.handleSuccess();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.e("测试", "onError()");
        String msg;
        if (e instanceof UnknownHostException) {
            msg = "没有网络...";
        } else if (e instanceof SocketTimeoutException) {
           msg = "请求超时...";
        }else if (e instanceof IllegalStateException) {
           msg = "数据解析异常...";
        }else{
            msg = "请求失败，请稍后重试...";
        }
        if (mBasePresenter != null){
            mBasePresenter.handleError(e);
        }
    }
}
