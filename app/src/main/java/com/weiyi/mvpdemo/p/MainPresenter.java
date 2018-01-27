package com.weiyi.mvpdemo.p;

import android.util.Log;

import com.weiyi.mvpdemo.m.bean.CheckversionBean;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.utils.RxSchedulersHelper;
import com.weiyi.mvpdemo.utils.RxSubscriber;
import com.weiyi.mvpdemo.v.MainActivity;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Lee on 2018/1/25 0025.
 */

public class MainPresenter extends BasePresenter<MainActivity> {

    public void request() {
        mApiServers.checkVersion("1", "1", "0")
                .compose(RxSchedulersHelper.<CheckversionBean>io_main())
                .subscribe(new RxSubscriber<CheckversionBean>() {
                    @Override
                    public void _onNext(CheckversionBean s) {
                        Log.e("测试", s.toString());
                    }

                    @Override
                    public void _onError(String msg) {
                        Log.e("测试", msg);
                    }
                });
    }

}
