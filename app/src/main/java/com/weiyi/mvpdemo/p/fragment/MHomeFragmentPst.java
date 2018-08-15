package com.weiyi.mvpdemo.p.fragment;

import android.widget.Toast;

import com.weiyi.mvpdemo.m.bean.MHomeBannersInfo;
import com.weiyi.mvpdemo.m.bean.MHomeGoodsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeNewsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeSeckillGoodsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeSeckillTimeInfo;
import com.weiyi.mvpdemo.m.bean.TestBean;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.utils.rxjava.RxSchedulersHelper;
import com.weiyi.mvpdemo.utils.rxjava.RxSubscriber;
import com.weiyi.mvpdemo.v.fragment.MHomeFragment;

import rx.Subscription;

/**
 * Created by Lee on 2018/2/28 0028.
 */
public class MHomeFragmentPst extends BasePresenter<MHomeFragment> {
    public void requestForBanners() {
        mApiServers.getHomeTopBanners(0)
                .compose(RxSchedulersHelper.<MHomeBannersInfo>io_main())
                .subscribe(new RxSubscriber<MHomeBannersInfo>(this) {
                    @Override
                    public void onNext(MHomeBannersInfo mHomeBannersInfo) {
                        mView.responseSuccessForTopBanners(mHomeBannersInfo);
                    }
                });
    }

    public void requestForGoodsList() {
        mApiServers.getHomeGoodsList("xl")
                .compose(RxSchedulersHelper.<MHomeGoodsInfo>io_main())
                .subscribe(new RxSubscriber<MHomeGoodsInfo>() {
                    @Override
                    public void onNext(MHomeGoodsInfo mHomeGoodsInfo) {
                        mView.responseSuccessForGoodsList(mHomeGoodsInfo);
                    }

                });
    }

    public void requestForNewsList() {
        mApiServers.getNewsList(0)
                .compose(RxSchedulersHelper.<MHomeNewsInfo>io_main())
                .subscribe(new RxSubscriber<MHomeNewsInfo>() {
                    @Override
                    public void onNext(MHomeNewsInfo mHomeNewsInfo) {
                        mView.responseSuccessForNewsList(mHomeNewsInfo);
                    }

                });
    }

    public void requestForSeckillGoodsList() {
        Subscription subscribe = mApiServers.getSeckillGoodsList(0)
                .compose(RxSchedulersHelper.<MHomeSeckillGoodsInfo>io_main())
                .subscribe(new RxSubscriber<MHomeSeckillGoodsInfo>() {
                    @Override
                    public void onNext(MHomeSeckillGoodsInfo mHomeSeckillGoodsInfo) {
                        mView.responseSuccessForSeckillGoodsList(mHomeSeckillGoodsInfo);

                    }
                });
    }

    public void requestForSeckillTimes() {
        mApiServers.getSeckillTimes(0)
                .compose(RxSchedulersHelper.<MHomeSeckillTimeInfo>io_main())
                .subscribe(new RxSubscriber<MHomeSeckillTimeInfo>() {
                    @Override
                    public void onNext(MHomeSeckillTimeInfo mHomeSeckillTimeInfo) {
                        mView.responseSuccessForSeckillTimes(mHomeSeckillTimeInfo);

                    }

                });
    }

    public void test() {
        mApiServers.textAPI(0)
                .compose(RxSchedulersHelper.<TestBean>io_main())
                .subscribe(new RxSubscriber<TestBean>() {
                    @Override
                    public void onNext(TestBean testBean) {

                    }

                });
    }

    public void testA() {
        mApiServers.textA("17703951085")
                .compose(RxSchedulersHelper.<String>io_main())
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void onNext(String s) {

                    }
                });

    }


}
