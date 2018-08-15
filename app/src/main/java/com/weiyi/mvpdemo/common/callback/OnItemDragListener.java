package com.weiyi.mvpdemo.common.callback;

/**
 * ProjectName：DemoMVP
 * DESC: (recycleView 的条目 拖动事件回调)
 * Created by 李岩 on 2018/5/25 0025
 *
 */
public interface OnItemDragListener {

    /**
     * 当Item 上下拖动
     * @param dragPosition 被操作的Item position
     * @param targetPosition 目标Item position
     */
    void onStartDrag(int dragPosition, int targetPosition);
    /**
     * 当Item 上下拖动
     * @param position 被操作的Item position
     */
    void onSwipe(int position);
}
