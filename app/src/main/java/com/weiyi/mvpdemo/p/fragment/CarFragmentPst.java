package com.weiyi.mvpdemo.p.fragment;

import com.weiyi.mvpdemo.m.bean.CartDataBean;
import com.weiyi.mvpdemo.m.bean.GoodsDataBean;
import com.weiyi.mvpdemo.m.bean.MHomeGoodsInfo;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.p.base.ShopCartRes;
import com.weiyi.mvpdemo.utils.rxjava.RxSchedulersHelper;
import com.weiyi.mvpdemo.utils.rxjava.RxSubscriber;
import com.weiyi.mvpdemo.v.fragment.CarFragment;

import java.util.LinkedList;

/**
 * Created by Lee on 2018/2/28 0028.
 */
public class CarFragmentPst extends BasePresenter<CarFragment> {
    public void requestCartData() {
        mApiServers.getCarDatas("xxx")
                .compose(RxSchedulersHelper.<ShopCartRes>io_main())
                .subscribe(new RxSubscriber<ShopCartRes>() {
                    @Override
                    public void _onNext(ShopCartRes shopCartRes) {
                        mView.responseSuccessForCarDatas(shopCartRes);
                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }

    public void requestGoodsListData() {
        mApiServers.getHomeGoodsList("xl")
                .compose(RxSchedulersHelper.<MHomeGoodsInfo>io_main())
                .subscribe(new RxSubscriber<MHomeGoodsInfo>() {
                    @Override
                    public void _onNext(MHomeGoodsInfo mHomeGoodsInfo) {
                        LinkedList<CartDataBean> mGoodsDatas = new LinkedList<CartDataBean>();
                        for (GoodsDataBean obj : mHomeGoodsInfo.data) {
                            CartDataBean goodsInfo = new CartDataBean();
                            goodsInfo.is_checked = true;
                            goodsInfo.goods_name = obj.goods_name;
                            goodsInfo.is_attr = (obj.is_attr == 1 ? true : false);
                            goodsInfo.goods_thumb = obj.goods_thumb;
                            goodsInfo.count = 1;
                            goodsInfo.goods_id = obj.goods_id;
                            goodsInfo.goods_price = obj.shop_price;
                            goodsInfo.pinned_title = "为您推荐";
                            goodsInfo.type = mView.GridFlag;
                            mGoodsDatas.add(goodsInfo);
                        }
                        mView.responseSuccessForGoodsList(mGoodsDatas);
                    }

                    @Override
                    public void _onError(String msg) {

                    }
                });
    }
}
