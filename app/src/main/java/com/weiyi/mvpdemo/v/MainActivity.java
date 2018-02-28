package com.weiyi.mvpdemo.v;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.tab.Tabs;
import com.weiyi.mvpdemo.common.widget.TabFragmentHost;
import com.weiyi.mvpdemo.p.MainPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Bind(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @Bind(android.R.id.tabs)
    TabWidget mTabs;
    @Bind(R.id.tabFragmentHost)
    TabFragmentHost mTabFragmentHost;
    private Tabs.Tab[] mMainTabs;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onViewBinding() {
//        mToolbar.setTitle("主页");
//        setSupportActionBar(mToolbar);
//        mToolbar.setNavigationIcon(R.mipmap.icon_me);
//        mPresenter.request();
//        mBtnTablayout.getText();

        mMainTabs = Tabs.values();

        mTabFragmentHost.setup(mActivity, getSupportFragmentManager(), android.R.id.tabcontent);
        if (Build.VERSION.SDK_INT > 10){
            mTabFragmentHost.getTabWidget().setShowDividers(0);
        }

        for (int i = 0; i < mMainTabs.length; ++i) {
            TabHost.TabSpec tabSpec = mTabFragmentHost.newTabSpec(mMainTabs[i].tabName);
            TextView textView = new TextView(mActivity);
            textView.setGravity(Gravity.CENTER);
            textView.setText(mMainTabs[i].tabName);
            textView.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(mMainTabs[i].drawRes), null, null);
            tabSpec.setIndicator(textView);
            mTabFragmentHost.addTab(tabSpec, mMainTabs[i].clz, null);
        }
    }

}
