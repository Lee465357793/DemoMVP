package com.weiyi.mvpdemo.v;

import android.support.v7.widget.Toolbar;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.TableLayoutPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import butterknife.Bind;

public class TabLayoutActivity extends BaseActivity<TableLayoutPresenter> {
    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    public TableLayoutPresenter getPresenter() {
        return new TableLayoutPresenter();
    }

    @Override
    protected void onViewBinding() {
        mToolbar.setTitle("主页");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.icon_me);
    }
}
