package com.weiyi.mvpdemo.v;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.MainPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Bind(R.id.btn_tablayout)
    Button mBtnTablayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onViewBinding() {
        mPresenter.request();
        mBtnTablayout.getText();
    }


    @OnClick(R.id.btn_tablayout)
    public void onViewClicked() {
        mPresenter.request();
        startActivity(new Intent(this, TabLayoutActivity.class));
    }
}
