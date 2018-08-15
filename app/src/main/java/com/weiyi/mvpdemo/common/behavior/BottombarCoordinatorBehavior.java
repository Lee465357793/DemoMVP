package com.weiyi.mvpdemo.common.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.weiyi.mvpdemo.common.widget.FollowFrameLayout;

/**
 * Created by Lee on 2018/2/25 0025.
 */

public class BottombarCoordinatorBehavior extends CoordinatorLayout.Behavior {

    private int mScreenWidth;

    public BottombarCoordinatorBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 表示是否给应用了Behavior 的View 指定一个依赖的布局，通常，当依赖的View 布局发生变化时
     * 不管被被依赖View 的顺序怎样，被依赖的View也会重新布局
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * 当 dependency_view 发生改变时（位置、高度）
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //根据 dependency_view 的位置 设置  child_view 的位置
        int top = dependency.getTop();
        child.setTranslationY(-top);
        return true;
    }

    private void setPosition(View child, int offsetX, int top) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = offsetX;
        layoutParams.topMargin = top;
        child.setLayoutParams(layoutParams);
    }
}
