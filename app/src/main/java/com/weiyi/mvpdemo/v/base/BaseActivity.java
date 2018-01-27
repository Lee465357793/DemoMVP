package com.weiyi.mvpdemo.v.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.weiyi.mvpdemo.p.base.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by Lee on 2018/1/25 0025.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView{
    public T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = getPresenter();
        mPresenter.attachView(this, this);

        onViewBinding();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    /**
     * 初始化布局ID
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化当前View 的 presenter
     * @return
     */
    public abstract T getPresenter();

    /**
     * 初始化
     */
    protected abstract void onViewBinding();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
