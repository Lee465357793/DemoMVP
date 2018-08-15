package com.weiyi.mvpdemo.common.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.weiyi.mvpdemo.common.widget.FollowFrameLayout;

/**
 * Created by Lee on 2018/2/25 0025.
 */

public class CoordinatorBehavior extends CoordinatorLayout.Behavior<Button> {

    private int mScreenWidth;

    public CoordinatorBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
    }

    /**
     * 判断  child布局  是否是要求的  dipendency布局
     * @param parent
     * @param child 绑定Behavior 的View
     * @param dependency 被依赖的View
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof FollowFrameLayout;
    }

    /**
     * 当 被依赖的 view 发生改变时（位置、高度）
     * @param parent
     * @param child 绑定Behavior 的View
     * @param dependency 被依赖的 view
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        //根据 dependency_view 的位置 设置  child_view 的位置
        int top = dependency.getTop();
        int left = dependency.getLeft();

        int offsetX = mScreenWidth - left - child.getWidth();
        setPosition(child, offsetX, top);

        Log.e("测试", "offsetX=" + offsetX);
        return true;
    }

    private void setPosition(View child, int offsetX, int top) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = offsetX;
        layoutParams.topMargin = top;
        child.setLayoutParams(layoutParams);
    }
}
