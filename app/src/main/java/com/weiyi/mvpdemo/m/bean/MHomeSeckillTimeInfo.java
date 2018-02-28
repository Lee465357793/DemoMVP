package com.weiyi.mvpdemo.m.bean;

import java.util.List;

/**
 * Created by Lee on 2017/7/26.
 */

public class MHomeSeckillTimeInfo {

    /**
     * info : ok
     * kill_time_end : [1501034400,1501041600,1501048800,1501056000,1501063200]
     * kill_time_start : [1501030800,1501038000,1501045200,1501052400,1501059600]
     * now_time : 1501041645
     * status : 1
     */
    public String info;
    public long now_time;
    public int status;
    public List<Long> kill_time_end;
    public List<Long> kill_time_start;
}