package com.weiyi.mvpdemo.m.bean;

import java.util.List;

/**
 * Created by Lee on 2017/10/10 0010.
 */

public class MHomeBannersInfo {

    /**
     * code : 200
     * message : success
     * data : [{"ad_code":"http://imgapp.paicl.net/data/afficheimg/1503512269768343175.jpg","app_type":"1","ad_link":"","app_type_desc":"秒杀专区"},{"ad_code":"http://imgapp.paicl.net/data/afficheimg/1503530745521542807.jpg","app_type":"3","ad_link":"http://apilela.lelamall.net/mobile_web/banner/zhuangshi/index.html","app_type_desc":"此链接"},{"ad_code":"http://imgapp.paicl.net/data/afficheimg/1507330776281100687.jpg","app_type":"400","ad_link":"","app_type_desc":"暂时不跳"},{"ad_code":"http://imgapp.paicl.net/data/afficheimg/1505349108201354627.jpg","app_type":"2","ad_link":"","app_type_desc":"新人特惠半价专区"}]
     * banner : http://imgapp.paicl.net/data/afficheimg/1506033115399420455.jpg
     * res_ad1 : [{"ad_code":"http://imgapp.paicl.net/data/afficheimg/1499194632772804949.jpg"}]
     * bonus_img : http://imgapp.paicl.net/data/afficheimg/1505932102018249545.png
     */
    public int code;
    public String message;
    public String banner;
    public String bonus_img;
    public List<DataBean> data;
    public List<ResAd1Bean> res_ad1;


    public class DataBean {
        /**
         * ad_code : http://imgapp.paicl.net/data/afficheimg/1503512269768343175.jpg
         * app_type : 1
         * ad_link :
         * app_type_desc : 秒杀专区
         */
        public String ad_code;
        public String app_type;
        public String ad_link;
        public String app_type_desc;
    }

    public class ResAd1Bean {
        /**
         * ad_code : http://imgapp.paicl.net/data/afficheimg/1499194632772804949.jpg
         */
        public String app_type_desc;
    }
}
