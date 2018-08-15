package com.weiyi.mvpdemo.p.base;

import android.content.Context;

import com.weiyi.mvpdemo.MyAppLication;
import com.weiyi.mvpdemo.m.ApiServers;
import com.weiyi.mvpdemo.m.ErrorHelper;
import com.weiyi.mvpdemo.m.HttpUtils;
import com.weiyi.mvpdemo.v.base.BaseActivity;
import com.weiyi.mvpdemo.v.base.IBaseView;

import java.lang.ref.WeakReference;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 eated by Lee on 2018/1/25 0025.
 */
public class BasePresenter<VIEW extends IBaseView> {
    /**
     * 服务器  数据接口
     */
    public ApiServers mApiServers = HttpUtils.getInstence();//初始化Retrofit
    public VIEW mView;
    private WeakReference<VIEW> mReference;
    public BaseActivity mContext;
    public Context getContext(){
        return mContext;
    }

    /**
     * 绑定View
     * @param view
     * @param context
     */
    public void attachView(VIEW view, BaseActivity context) {
        mReference = new WeakReference<VIEW>(view);
        mView = mReference.get();
        mContext = context;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        if (mReference != null){
            mReference.clear();
        }
    }

    /**
     * 网络请求开始
     */
    public void start() {
        if (mContext != null){
            mContext.showLoading("");
        }
    }

    /**
     * 网络错误提示Error类
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        if (mContext != null){
            mContext.hideLoading();
            ErrorHelper.onError(mContext,throwable);
        }
    }

    /**
     * 网络链接成功
     */
    public void handleSuccess(){
        if (mContext != null){
            mContext.hideLoading();
        }
    }

}
