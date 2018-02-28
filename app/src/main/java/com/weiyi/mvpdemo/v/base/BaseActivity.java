package com.weiyi.mvpdemo.v.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.utils.ThemeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lee on 2018/1/25 0025.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView{
    public T mPresenter;
    public static int themeIndex = 1;
    public BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(getLayoutResId());
        mActivity = this;
        mPresenter = getPresenter();
        mPresenter.attachView(this, this);
        onViewBinding();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    private void initTheme() {
        ThemeUtils.Theme theme = ThemeUtils.getCurrentTheme(this);
        ThemeUtils.changeTheme(this, theme);
    }

    /**
     * 初始化布局ID
     * @return
     */
    protected abstract int getLayoutResId();
//    /**
//     * 设置布局ID
//     * @return
//     */
//    protected void setContentViewResId(){
//
//    }

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
