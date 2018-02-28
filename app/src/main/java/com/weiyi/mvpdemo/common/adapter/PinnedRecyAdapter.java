package com.weiyi.mvpdemo.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.m.bean.CartDataBean;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cn.jpush.android.e.m;

/**
* RecyclerView的Adapter
* 购物车 商品 + 推荐商品  展示
* 作者：cg 修改: lee
* 时间：2016/8/18 0018 上午 10:47
*/
public class PinnedRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CartDataBean> list_data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;
    //分别定义三种显示格式的值
    private static final int GridFlag = 1;
    private static final int ListFlag = 2;
    private static final int titleFlag = 3;
    private static final int ListHintFlag = 4;
    private HashMap<String, String> mParams = new HashMap<>();
    private boolean mIsStoreChecked;

    public PinnedRecyAdapter(List<CartDataBean> list_data, Context context) {
        if (list_data != null){
            this.list_data.addAll(list_data);
        }
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setList_data(List<CartDataBean> list_data) {
        this.list_data.clear();
        this.list_data.addAll(list_data);
        notifyDataSetChanged();
    }

    public List<CartDataBean> getList_data(){
        return list_data;
    }

    /**
     * 定义点击每项的接口,此处只实现了点击，没有实现长按
     */
    public interface OnItemClickLitener
    {
        /**
         * 条目点击监听
         * @param view
         * @param positon
         * @param type 条目类型
         */
        void OnItemClick(View view, int positon, int type);
        void OnItemLongClick(View view, int position);

        /**
         * 单选监听
         * @param isChecked 是否选中
         * @param position 变动的position
         */
        void onCheckedChangeListener(boolean isChecked, int position);

        /**
         * 全选 监听
         * @param isChecked
         */
        void onAllCheckedChangeListener(boolean isChecked);

        /**
         * 商品数量改变监听
         * @param position 变动的position
         * @param isChecked 改变后的数量
         */
        void onGoodsNumberChanges(int position, boolean isChecked);

        void onAddCartSuccess();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        RecyclerView.ViewHolder mViewHolder;
        if(viewType==GridFlag) {

            view = inflater.inflate(R.layout.layout_pinned_griditem, parent, false);
            mViewHolder = new gViewHolder(view);
        }else if(viewType==ListFlag) {
            view = inflater.inflate(R.layout.layout_pinned_listitem, parent, false);
            mViewHolder = new lViewHolder(view);
        }else if (viewType == ListHintFlag){
            view = inflater.inflate(R.layout.layout_pinned_carthead, parent, false);
            mViewHolder = new tHintViewHolder(view);
        }else {
            view = inflater.inflate(R.layout.layout_pinned_titleitem, parent, false);
            mViewHolder = new tViewHolder(view);
        }

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position))
        {
            case GridFlag:
                final gViewHolder gHolder = (gViewHolder)holder;
                Glide.with(mContext).load(list_data.get(position).goods_thumb).into(gHolder.ivGoodsImg);
                gHolder.mTvGoodsName.setText(list_data.get(position).goods_name);
                gHolder.mTvGoodsPrice.setText(list_data.get(position).goods_price);
                gHolder.mItemAddCar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });

                if(mOnItemClickLitener!=null) {

                    gHolder.linear_g.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mOnItemClickLitener.OnItemClick(gHolder.itemView, position, GridFlag);
                        }
                    });
                }
                break;
            case ListHintFlag:
                final tHintViewHolder  tHintHolder = (tHintViewHolder)holder;
                tHintHolder.mLlLoginHint.setVisibility(View.VISIBLE);
                tHintHolder.mLoginHintLine.setVisibility(View.VISIBLE);

                break;
            case titleFlag:
                final tViewHolder tHolder = (tViewHolder)holder;
                tHolder.txt_pTitle.setText(list_data.get(position).pinned_title);
                if ("乐拉商城".equals(list_data.get(position).pinned_title)){
                    tHolder.mLlGoodsTitle.setVisibility(View.GONE);
                    tHolder.mLlStoreTitle.setVisibility(View.VISIBLE);
                }else {
                    tHolder.mLlStoreTitle.setVisibility(View.GONE);
                    tHolder.mLlGoodsTitle.setVisibility(View.VISIBLE);
                }

                tHolder.mCktStore.setChecked(list_data.get(position).is_checked);

                tHolder.mCktStore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (list_data.get(position).is_checked){ //选中 ==>  取消选中
                            tHolder.mCktStore.setChecked(false);
                            list_data.get(position).is_checked = false;
                        }else {//取消选中 ==>  选中
                            tHolder.mCktStore.setChecked(true);
                            list_data.get(position).is_checked = true;
                        }

                        if (mOnItemClickLitener != null){ //商品全选监听
                            mOnItemClickLitener.onAllCheckedChangeListener(list_data.get(position).is_checked);
                        }
                    }
                });

                break;
            case ListFlag:
                final lViewHolder lHolder = (lViewHolder)holder;
                lHolder.mTvProductName.setText(list_data.get(position).goods_name);
                lHolder.mTvProductDesc.setText(list_data.get(position).goods_desc);
                lHolder.mTvBuyCount.setText(String.valueOf(list_data.get(position).count));
                lHolder.mTvPrice.setText(list_data.get(position).goods_price);
                lHolder.mCbCheck.setChecked(list_data.get(position).is_checked);
                Glide.with(mContext).load(list_data.get(position).goods_thumb).into(lHolder.mGoodsPic);
                lHolder.mGoodsPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });

                lHolder.mCbCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (list_data.get(position).is_checked){ //选中 ==>  取消选中
                            lHolder.mCbCheck.setChecked(false);
                            list_data.get(position).is_checked = false;
                        }else {//取消选中 ==>  选中
                            lHolder.mCbCheck.setChecked(true);
                            list_data.get(position).is_checked = true;
                        }
                        if (mOnItemClickLitener != null){//商品选中监听
                            int k = 0; //选中的商品数量
                            for (CartDataBean obj : list_data) {
                                if (obj.type == ListFlag && obj.is_checked){
                                    k++;
                                }
                            }
                            mOnItemClickLitener.onCheckedChangeListener(list_data.get(position).is_checked, position);
                            if (k <= 0){
                                list_data.get(0).is_checked = false;
                                notifyItemChanged(0);
                            }
                        }
                    }
                });


                lHolder.mIvDecrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = list_data.get(position).count;
                        --count;
                        final int finalCount = count;

                        Toast.makeText(mContext, String.valueOf(finalCount), Toast.LENGTH_SHORT).show();

                    }
                });
                lHolder.mIvIncrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = list_data.get(position).count;
                        ++count;
                        final int finalCount = count;
                        Toast.makeText(mContext, String.valueOf(finalCount), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {

        return list_data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    /**
     * 类别形式的布局数据
     */
    class tViewHolder extends RecyclerView.ViewHolder {

        ImageView ivTop;
        LinearLayout linear_t;
        LinearLayout mLlStoreTitle;
        LinearLayout mLlGoodsTitle;
        TextView txt_pTitle;
        CheckBox mCktStore;

        public tViewHolder(View itemView) {
            super(itemView);
            linear_t = (LinearLayout)itemView.findViewById(R.id.linear_t);
            mLlStoreTitle = (LinearLayout)itemView.findViewById(R.id.ll_store_title);
            mLlGoodsTitle = (LinearLayout)itemView.findViewById(R.id.ll_goods_title);
            txt_pTitle = (TextView)itemView.findViewById(R.id.txt_pTitle);
            mCktStore = (CheckBox)itemView.findViewById(R.id.ckt_store);
//            ivTop = (ImageView)itemView.findViewById(iv_top);
        }
    }

    /**
     * Grid形式的布局数据
     */
    class gViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGoodsImg;
        LinearLayout linear_g;
        TextView mTvIsSelfTag;
        TextView mTvGoodsName;
        TextView mTvGoodsPrice;
        ImageView mItemAddCar;

        public gViewHolder(View itemView) {
            super(itemView);

            linear_g = (LinearLayout)itemView.findViewById(R.id.pgrid_item_layout);
            ivGoodsImg = (ImageView)itemView.findViewById(R.id.pgrid_goods_pic);
            mItemAddCar = (ImageView)itemView.findViewById(R.id.pgrid_add_car);
            mTvIsSelfTag = (TextView)itemView.findViewById(R.id.pgrid_self_tag);
            mTvGoodsName = (TextView)itemView.findViewById(R.id.pgrid_goods_name);
            mTvGoodsPrice = (TextView)itemView.findViewById(R.id.pgrid_goods_price);
        }
    }

    /**
     * List形式的布局数据
     */
    class lViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rela_l;
        CheckBox mCbCheck;
        ImageView mGoodsPic;
        TextView mTvProductName;
        TextView mTvProductDesc;
        TextView mTvPrice;
        ImageView mIvDecrease;
        TextView mTvBuyCount;
        ImageView mIvIncrease;

        public lViewHolder(View itemView) {
            super(itemView);

            rela_l = (LinearLayout)itemView.findViewById(R.id.plist_rela_layout);
            mCbCheck = (CheckBox) itemView.findViewById(R.id.plist_goods_check);
            mGoodsPic = (ImageView) itemView.findViewById(R.id.plist_goods_pic);
            mTvProductName = (TextView)itemView.findViewById(R.id.plist_goods_name);
            mTvProductDesc = (TextView)itemView.findViewById(R.id.plist_goods_desc);
            mTvPrice = (TextView)itemView.findViewById(R.id.plist_goods_price);
            mTvBuyCount = (TextView)itemView.findViewById(R.id.plist_goods_count);
            mIvDecrease = (ImageView)itemView.findViewById(R.id.plist_decrease);
            mIvIncrease = (ImageView)itemView.findViewById(R.id.plist_increase);
        }
    }


    private class tHintViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mLogin;
        TextView mToSeckill;
        LinearLayout mLlLoginHint;
        ImageView mLoginHintLine;

        public tHintViewHolder(View view) {
            super(view);

            mLogin = (TextView) view.findViewById(R.id.phead_logining);
            mToSeckill = (TextView) view.findViewById(R.id.phead_seckill);
            mLlLoginHint = (LinearLayout) view.findViewById(R.id.phead_login_layout);
            mLoginHintLine = (ImageView) view.findViewById(R.id.phead_line);
            mLogin.setOnClickListener(this);
            mToSeckill.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.phead_logining:
                    Toast.makeText(mContext, "logining", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.phead_seckill:
                    Toast.makeText(mContext, "seckill", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
