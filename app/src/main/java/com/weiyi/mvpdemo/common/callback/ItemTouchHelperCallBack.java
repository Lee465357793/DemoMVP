package com.weiyi.mvpdemo.common.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * ProjectName：DemoMVP
 * DESC: (类描述)
 * Created by 李岩 on 2018/5/25 0025
 * updateName:(修改人名称)
 * updateTime:(修改时间)
 * updateDesc:(修改内容)
 */
public class ItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    private final OnItemDragListener onDragListener;

    public ItemTouchHelperCallBack(OnItemDragListener onDragListener) {
        this.onDragListener = onDragListener;
    }

    /**
     * 获取活动 Flags,
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags =  ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 当滑动时
     * @param recyclerView
     * @param viewHolder 被拖动的Item 的ViewHolder
     * @param target 目标位置的Item 的ViewHolder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //获取需要调换的Item 的 position, 调换数据
        onDragListener.onStartDrag(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    /**
     * 当拖动时
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        onDragListener.onSwipe(viewHolder.getAdapterPosition());
    }
}
