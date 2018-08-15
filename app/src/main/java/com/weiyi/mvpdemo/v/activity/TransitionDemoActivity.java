package com.weiyi.mvpdemo.v.activity;

import android.os.Bundle;
import android.widget.Button;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionDemoActivity extends BaseActivity {

    @BindView(R.id.transition_a)
    Button mTransitionA;
    @BindView(R.id.transition_b)
    Button mTransitionB;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_transition_demo;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onViewBinding() {

    }

}
