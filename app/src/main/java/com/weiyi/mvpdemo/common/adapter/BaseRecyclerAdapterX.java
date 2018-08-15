package com.weiyi.mvpdemo.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.weiyi.mvpdemo.common.callback.OnItemDragListener;
import com.weiyi.mvpdemo.utils.CacheUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.internal.Utils;

/**
 * ProjectName：DemoMVP
 * DESC: (类描述)
 * Created by 李岩 on 2018/5/25 0025
 * updateName:(修改人名称)
 * updateTime:(修改时间)
 * updateDesc:(修改内容)
 */
public abstract class BaseRecyclerAdapterX<T> extends RecyclerView.Adapter<BaseRecyclerAdapterX.BaseViewHolder> implements OnItemDragListener {
    private Context mContext;
    private int mLayoutId;
    private ArrayList<T> mListDatas = new ArrayList<>();

    public BaseRecyclerAdapterX(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
    }

    public void setListDatas(List<T> datas) {
        mListDatas.clear();
        mListDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void loadMoreListDatas(ArrayList<T> datas) {
        mListDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public BaseRecyclerAdapterX.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, null));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapterX.BaseViewHolder holder, int position){
        _onBindViewHolder(holder, position, mListDatas.get(position));
    }

    protected abstract void _onBindViewHolder(BaseViewHolder holder, int position, T t);

    @Override
    public void onStartDrag(int dragPosition, int targetPosition) {
        Collections.swap(mListDatas, dragPosition, targetPosition);
        notifyItemMoved(dragPosition, targetPosition);
    }

    @Override
    public void onSwipe(int position) {
        mListDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mListDatas.size();
    }

    public T getItemData(int position){
        return mListDatas.get(position);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder{

        private View mItemView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
        }

        public View getView(int viewId){
            return mItemView.findViewById(viewId);

        }
        public void setText(int viewId, String text){
            View viewById = mItemView.findViewById(viewId);
            if (viewById instanceof TextView){
                ((TextView)viewById).setText(text);
            }
        }

        public void setImageResouce(int viewId, int resId){
            View viewById = mItemView.findViewById(viewId);
            if (viewById instanceof ImageView){
                ((ImageView) viewById).setImageResource(resId);
            }
        }

        public void setOnClick(int viewId, View.OnClickListener onClickListener){
            View viewById = mItemView.findViewById(viewId);
            viewById.setOnClickListener(onClickListener);
        }
    }
}
