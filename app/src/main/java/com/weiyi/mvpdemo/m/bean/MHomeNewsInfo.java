package com.weiyi.mvpdemo.m.bean;

import java.util.List;

/**
 * Created by Lee on 2017/10/10 0010.
 */

public class MHomeNewsInfo {

    /**
     * code : 200
     * message : success
     * data : [{"title":"乐哩连锁超市，庆双节8折风暴即将结束！","file_url":"http://imgapp.paicl.net/data/article/1507231239583712363.png","add_time":"1507231239","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=403","keywords":"","description":"活动即将结束，没买的抓紧时间了！","article_type":"1"},{"title":"送苹果、送月饼、送大肉块、送牛奶 | 乐拉妹献给粉丝们的豪礼","file_url":"http://imgapp.paicl.net/data/article/1505238854914613627.png","add_time":"1505238854","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=357","keywords":"","description":"双节同欢，豪礼不断！","article_type":"1"},{"title":"浓情九月、勿忘师恩 乐拉商城感恩教师节","file_url":"http://imgapp.paicl.net/data/article/1504918132159699731.png","add_time":"1504918132","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=346","keywords":"","description":"感恩教师节，苹果来袭！","article_type":"1"},{"title":"乐拉商城充3000送1000活动火爆来袭！","file_url":"http://imgapp.paicl.net/data/article/1504573049278226974.png","add_time":"1504573049","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=342","keywords":"","description":"#充值返现#又来啦！！！","article_type":"1"},{"title":"#最后一天#乐拉商城半价风暴即将结束！！！","file_url":"http://imgapp.paicl.net/data/article/1504132192409124626.png","add_time":"1504132192","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=328","keywords":"","description":"乐拉商城5折风暴即将结束！！！","article_type":"1"},{"title":"这个七夕，乐拉商城半价风暴来袭，不要说我不爱你呦！","file_url":"http://imgapp.paicl.net/data/article/1503515271330827321.jpg","add_time":"1503515271","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=309","keywords":"","description":"约惠七夕 为爱放价","article_type":"1"},{"title":"重要通知：乐拉商城计划在25号晚关闭一元夺宝","file_url":"http://imgapp.paicl.net/data/article/1503509023330786570.jpg","add_time":"1503509023","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=308","keywords":"","description":"25号系统将返还用户正在参与一元夺宝活动的金额！","article_type":"1"},{"title":"大伟装饰集团携手乐拉商城钜惠全城！！！","file_url":"http://imgapp.paicl.net/data/article/1502492240812976790.jpg","add_time":"1502406187","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=275","keywords":"","description":"你装修，我补贴，全城钜惠！","article_type":"1"},{"title":"我们开店，您赚钱！乐哩连锁招募合伙人","file_url":"http://imgapp.paicl.net/data/article/1502406784843917710.png","add_time":"1502219583","article_id":"http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=267","keywords":"","description":"高薪诚聘 选择我们 成就自己\r\n","article_type":"1"}]
     */
    private int code;
    private String message;
    private List<DataBean> data;


    public class DataBean {
        /**
         * title : 乐哩连锁超市，庆双节8折风暴即将结束！
         * file_url : http://imgapp.paicl.net/data/article/1507231239583712363.png
         * add_time : 1507231239
         * article_id : http://apilela.lelamall.net/mobile/index.php?m=default_app&c=article&a=info&aid=403
         * keywords :
         * description : 活动即将结束，没买的抓紧时间了！
         * article_type : 1
         */
        private String title;
        private String article_type;
    }
}
