package com.weiyi.mvpdemo.p;

import android.util.Log;

import com.weiyi.mvpdemo.m.bean.AppVersionInfo;
import com.weiyi.mvpdemo.m.bean.CheckversionBean;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.utils.rxjava.RxSchedulersHelper;
import com.weiyi.mvpdemo.utils.rxjava.RxSubscriber;
import com.weiyi.mvpdemo.v.MainActivity;

/**
 * Created by Lee on 2018/1/25 0025.
 */

public class MainPresenter extends BasePresenter<MainActivity> {

    public void request() {
        mApiServers.checkVersion(0)
                .compose(RxSchedulersHelper.<AppVersionInfo>io_main())
                .subscribe(new RxSubscriber<AppVersionInfo>() {
                    @Override
                    public void _onNext(AppVersionInfo s) {
                        Log.e("测试", s.toString());
                    }

                    @Override
                    public void _onError(String msg) {
                        Log.e("测试", msg);
                    }
                });
    }

}
