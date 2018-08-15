package com.weiyi.mvpdemo.v.activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.callback.ItemTouchHelperCallBack;
import com.weiyi.mvpdemo.common.callback.OnItemDragListener;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private GridRecyAdapter mGridAdapter;
    private int[] drawableRes = new int[]{R.mipmap.bh, R.mipmap.cuteboy, R.mipmap.cutegirl, R.mipmap.hxy, R.mipmap.lly, R.mipmap.sf, R.mipmap.xkl};
    private ArrayList<Integer> mIntegers;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setEnterTransition(new Slide(Gravity.BOTTOM));
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.anim_transition_enter));
        getWindow().setReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.anim_transition_return));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_anim;
    }

    @Override
    public BasePresenter getPresenter() {
        return new BasePresenter();
    }

    @Override
    protected void onViewBinding() {
        mIntegers = new ArrayList<>();

        for (int i : drawableRes) {
            mIntegers.add(i);
        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mGridAdapter = new GridRecyAdapter();
        mRecyclerView.setAdapter(mGridAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallBack(mGridAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }


    class GridRecyAdapter extends RecyclerView.Adapter<GridViewHolder> implements OnItemDragListener{

        @Override
        public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GridViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_grid, null));
        }

        @Override
        public void onBindViewHolder(final GridViewHolder holder, int position) {
            final int i = position % drawableRes.length;
            holder.mItemCardTopPic.setImageResource(mIntegers.get(i));
            holder.mItemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(new Intent(mActivity, ImgViewPagerActivity.class).putExtra("index", i), ActivityOptions.makeSceneTransitionAnimation(mActivity, holder.mItemCardTopPic, "view_pager_img").toBundle());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        @Override
        public void onStartDrag(int dragPosition, int targetPosition) {
            Collections.swap(mIntegers, dragPosition, targetPosition);
            notifyItemMoved(dragPosition, targetPosition);
        }

        @Override
        public void onSwipe(int position) {
            mIntegers.remove(position);
            notifyItemRemoved(position);
        }
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_card_view)
        CardView mItemCardView;
        @BindView(R.id.item_card_top_pic)
        ImageView mItemCardTopPic;
        public GridViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }
}
