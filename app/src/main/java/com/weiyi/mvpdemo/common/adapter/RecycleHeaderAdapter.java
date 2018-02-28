package com.weiyi.mvpdemo.common.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.m.bean.GoodsDataBean;
import com.youth.banner.loader.ImageLoader;


/**
 * Created by qibin on 2015/11/7.
 */
public class RecycleHeaderAdapter extends BaseRecyclerAdapter<GoodsDataBean> {

    public RecycleHeaderAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(RecyViewHolder holder, GoodsDataBean obj) {
        holder.setText(R.id.item_goods_name, obj.goods_name);
        holder.setText(R.id.item_goods_price, obj.shop_price);
        Glide.with(context).load(obj.goods_thumb).into((ImageView) holder.getView(R.id.item_goods_pic));
    }

    @Override
    public void onBind(RecyViewHolder viewHolder, int RealPosition, GoodsDataBean data) {

    }


}