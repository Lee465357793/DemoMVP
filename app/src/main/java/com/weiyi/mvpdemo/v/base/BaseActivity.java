package com.weiyi.mvpdemo.v.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.widget.ProgressDialogs;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.utils.ThemeUtils;

import butterknife.ButterKnife;

/**
 * Created by Lee on 2018/1/25 0025.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView{
    public T mPresenter;
    public static int themeIndex = 1;
    public BaseActivity mActivity;
    private ProgressDialogs mProgressDialogs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide(Gravity.LEFT).setDuration(400));
            //            getWindow().setReturnTransition(new Slide(Gravity.LEFT).setDuration(400)); //返回
            //            getWindow().setReenterTransition(new Slide(Gravity.LEFT).setDuration(400));
            //            getWindow().setExitTransition(new Slide(Gravity.LEFT).setDuration(400));

            //            getWindow().setSharedElementEnterTransition(new ChangeBounds());
            //            getWindow().setSharedElementExitTransition(new ChangeTransform());
            //            getWindow().setSharedElementReenterTransition(new ChangeTransform());
            //            getWindow().setSharedElementReturnTransition(new ChangeTransform());
        }
        setContentView(getLayoutResId());
        mProgressDialogs = new ProgressDialogs(this);
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


    public void showLoading(String msg) {
        if (mProgressDialogs != null){
            mProgressDialogs.showDialog();
        }
    }

    public void hideLoading() {
        if (mProgressDialogs != null){
            mProgressDialogs.closeDialog();
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            overridePendingTransition(R.anim.anim_exit_act_in, R.anim.anim_exit_act_out);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
