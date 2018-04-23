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

/**
 * Created by Lee on 2018/2/28 0028.
 */
public class MHomeFragmentPst extends BasePresenter<MHomeFragment> {
    public void requestForBanners() {
        mApiServers.getHomeTopBanners(0)
                .compose(RxSchedulersHelper.<MHomeBannersInfo>io_main())
                .subscribe(new RxSubscriber<MHomeBannersInfo>() {
                    @Override
                    public void _onNext(MHomeBannersInfo mHomeBannersInfo) {
                        mView.responseSuccessForTopBanners(mHomeBannersInfo);
                    }

                    @Override
                    public void _onError(String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void requestForGoodsList() {
        mApiServers.getHomeGoodsList("xl")
                .compose(RxSchedulersHelper.<MHomeGoodsInfo>io_main())
                .subscribe(new RxSubscriber<MHomeGoodsInfo>() {
                    @Override
                    public void _onNext(MHomeGoodsInfo mHomeGoodsInfo) {
                        mView.responseSuccessForGoodsList(mHomeGoodsInfo);
                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }

    public void requestForNewsList() {
        mApiServers.getNewsList(0)
                .compose(RxSchedulersHelper.<MHomeNewsInfo>io_main())
                .subscribe(new RxSubscriber<MHomeNewsInfo>() {
                    @Override
                    public void _onNext(MHomeNewsInfo mHomeNewsInfo) {
                        mView.responseSuccessForNewsList(mHomeNewsInfo);
                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }

    public void requestForSeckillGoodsList() {
        mApiServers.getSeckillGoodsList(0)
                .compose(RxSchedulersHelper.<MHomeSeckillGoodsInfo>io_main())
                .subscribe(new RxSubscriber<MHomeSeckillGoodsInfo>() {
                    @Override
                    public void _onNext(MHomeSeckillGoodsInfo mHomeSeckillGoodsInfo) {
                        mView.responseSuccessForSeckillGoodsList(mHomeSeckillGoodsInfo);
                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }

    public void requestForSeckillTimes() {
        mApiServers.getSeckillTimes(0)
                .compose(RxSchedulersHelper.<MHomeSeckillTimeInfo>io_main())
                .subscribe(new RxSubscriber<MHomeSeckillTimeInfo>() {
                    @Override
                    public void _onNext(MHomeSeckillTimeInfo mHomeSeckillTimeInfo) {
                        mView.responseSuccessForSeckillTimes(mHomeSeckillTimeInfo);
                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }

    public void test() {
        mApiServers.textAPI(0)
                .compose(RxSchedulersHelper.<TestBean>io_main())
                .subscribe(new RxSubscriber<TestBean>() {
                    @Override
                    public void _onNext(TestBean s) {

                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }

    public void testA() {
        mApiServers.textA("17703951085")
                .compose(RxSchedulersHelper.<String>io_main())
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void _onNext(String s) {

                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }
}
