package com.weiyi.mvpdemo.common.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.m.bean.GoodsDataBean;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Lee on 2017/5/20.
 */
public class GalleryAdapter extends BaseRecyclerAdapter<GoodsDataBean> {

    public GalleryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(RecyViewHolder holder, GoodsDataBean obj) {
        holder.setText(R.id.plist_goods_price, obj.sale_price);
        holder.setText(R.id.tv_market_price, obj.market_price);
        Glide.with(context).load(obj.goods_thumb).into((ImageView) holder.getView(R.id.iv_goods_pic));
    }

    @Override
    public void onBind(RecyViewHolder viewHolder, int RealPosition, GoodsDataBean data) {

    }

}
