package com.weiyi.mvpdemo.v;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.TableLayoutPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

public class TabLayoutActivity extends BaseActivity<TableLayoutPresenter> {


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

    }
}
