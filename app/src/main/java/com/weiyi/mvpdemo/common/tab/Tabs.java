package com.weiyi.mvpdemo.common.tab;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.v.fragment.CarFragment;
import com.weiyi.mvpdemo.v.fragment.MHomeFragment;
import com.weiyi.mvpdemo.v.fragment.TestFragment;

import static android.R.id.tabs;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Lee on 2018/2/28 0028.
 */

public class Tabs {

    static Tab[] tabs = new Tab[3];

    static {
        tabs[0] = new Tab(0, "测试", R.drawable.tab_home_selector, TestFragment.class);
        tabs[1] = new Tab(1, "首页", R.drawable.tab_home_selector, MHomeFragment.class);
        tabs[2] = new Tab(2, "购物车", R.drawable.tab_home_selector, CarFragment.class);
    }

    public static Tab[] values(){
     return tabs;
    }

    public static class Tab {

        public Tab(int idx, String tabName, int drawRes, Class clz) {
            this.idx = idx;
            this.tabName = tabName;
            this.drawRes = drawRes;
            this.clz = clz;
        }

        public int idx;
        public String tabName;
        public int  drawRes;
        public Class clz;

    }
}
