package com.weiyi.mvpdemo.m;

import com.weiyi.mvpdemo.m.bean.AppVersionInfo;
import com.weiyi.mvpdemo.m.bean.CartDataBean;
import com.weiyi.mvpdemo.m.bean.CheckversionBean;
import com.weiyi.mvpdemo.m.bean.GoodsDataBean;
import com.weiyi.mvpdemo.m.bean.MHomeBannersInfo;
import com.weiyi.mvpdemo.m.bean.MHomeGoodsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeNewsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeSeckillGoodsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeSeckillTimeInfo;
import com.weiyi.mvpdemo.p.base.ShopCartRes;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.Observer;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Lee on 2018/1/25 0025.
*/

public interface ApiServers {
    public static String BaseUrl = "http://apilela.lelamall.net/";

    @FormUrlEncoded
    @POST("appi/version_update.php")
    Observable<AppVersionInfo> checkVersion(@Field("") int temp);

    @FormUrlEncoded
    @POST("appi/shouyetu.php")
    Observable<MHomeBannersInfo> getHomeTopBanners(@Field("") int temp);

    @FormUrlEncoded
    @POST("appi/hot.php")
    Observable<MHomeGoodsInfo> getHomeGoodsList(@Field("sort") String sort);

    @FormUrlEncoded
    @POST("shun/peng_topline.php")
    Observable<MHomeNewsInfo> getNewsList(@Field("") int temp);

    @FormUrlEncoded
    @POST("appi/killProductList.php")
    Observable<MHomeSeckillGoodsInfo> getSeckillGoodsList(@Field("") int temp);

    @FormUrlEncoded
    @POST("appi/getKillTime.php")
    Observable<MHomeSeckillTimeInfo> getSeckillTimes(@Field("") int temp);

    @FormUrlEncoded
    @POST("appi/lcart.php")
    Observable<ShopCartRes> getCarDatas(@Field("user_id") String user_id);
}
