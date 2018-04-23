package com.weiyi.mvpdemo.v.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.adapter.BaseRecyclerAdapter;
import com.weiyi.mvpdemo.common.adapter.GalleryAdapter;
import com.weiyi.mvpdemo.common.adapter.RecycleHeaderAdapter;
import com.weiyi.mvpdemo.common.banner.GlideImageLoader;
import com.weiyi.mvpdemo.common.decoration.DividerGridItemDecoration;
import com.weiyi.mvpdemo.m.bean.MHomeBannersInfo;
import com.weiyi.mvpdemo.m.bean.MHomeGoodsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeNewsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeSeckillGoodsInfo;
import com.weiyi.mvpdemo.m.bean.MHomeSeckillTimeInfo;
import com.weiyi.mvpdemo.p.fragment.MHomeFragmentPst;
import com.weiyi.mvpdemo.v.activity.MainActivity;
import com.weiyi.mvpdemo.v.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by Lee on 2018/2/28 0028.
 */

public class MHomeFragment extends BaseFragment<MainActivity, MHomeFragmentPst> implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.recy_view)
    RecyclerView mRecyView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    private RecycleHeaderAdapter mAdapter;
    private GalleryAdapter mGalleryAdapter;
    private HeaderViewHolder mHeaderViewHolder;
    private int overallXScroll = 0;
    private int height = 340;// 滑动开始变色的高,真实项目中此高度是由广告轮播或其他首页view高度决定
    private int mCurrentSpace = 0;

    @Override
    public MHomeFragmentPst getPresenter() {
        return new MHomeFragmentPst();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mhome;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(mActivity), -1, -2);
        mSmartRefresh.setEnableLoadmore(true);
        mSmartRefresh.setOnRefreshListener(this);
        mSmartRefresh.setOnLoadmoreListener(this);

        mRecyView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyView.addItemDecoration(new DividerGridItemDecoration(mActivity));
        mAdapter = new RecycleHeaderAdapter(mActivity, R.layout.item_goods_layout);
        mRecyView.setAdapter(mAdapter);

        View headerView = LayoutInflater.from(mActivity).inflate(R.layout.layout_head_view, null);
        mHeaderViewHolder = new HeaderViewHolder(headerView);
        mAdapter.setHeaderView(headerView);

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Toast.makeText(mActivity, "position" + position + ",data" + data, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                overallXScroll = overallXScroll + dy;// 累加y值 解决滑动一半y值为0
                if (overallXScroll <= 0) {   //设置标题的背景颜色
                    mTitleLayout.setBackgroundColor(Color.argb((int) 0, 255, 67, 44));
                } else if (overallXScroll > 0 && overallXScroll <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) overallXScroll / height;
                    float alpha = (255 * scale);
                    mTitleLayout.setBackgroundColor(Color.argb((int) alpha, 255, 67, 44));
                } else {
                    mTitleLayout.setBackgroundColor(Color.argb(255, 255, 67, 44));
                }
            }
        });

        mPresenter.requestForBanners();
        mPresenter.requestForGoodsList();
        mPresenter.requestForNewsList();
        mPresenter.requestForSeckillGoodsList();
        mPresenter.requestForSeckillTimes();
        mPresenter.test();
        mPresenter.testA();
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.requestForBanners();
        mPresenter.requestForGoodsList();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mSmartRefresh.finishRefresh(1000);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> HTTP response

    public void responseSuccessForTopBanners(MHomeBannersInfo mHomeBannersInfo) {
        mHeaderViewHolder.mMhomeBanner.setImages(mHomeBannersInfo.data);
        mHeaderViewHolder.mMhomeBanner.start();
    }

    public void responseSuccessForGoodsList(MHomeGoodsInfo mHomeGoodsInfo) {
        mSmartRefresh.finishRefresh();
        mAdapter.setDatas(mHomeGoodsInfo.data);
    }

    public void responseSuccessForNewsList(MHomeNewsInfo mHomeNewsInfo) {

    }

    public void responseSuccessForSeckillGoodsList(MHomeSeckillGoodsInfo mHomeSeckillGoodsInfo) {
        if (mHomeSeckillGoodsInfo.status == 1){
            mGalleryAdapter.setDatas(mHomeSeckillGoodsInfo.list);
        }
    }

    public void responseSuccessForSeckillTimes(MHomeSeckillTimeInfo mHomeSeckillTimeInfo) {
        getCurrentTimeZoneOnAhead(mHomeSeckillTimeInfo, mHomeSeckillTimeInfo.now_time);
    }

    /**
     * 获取当前秒杀时间区间
     * @param mHomeSeckillTimeInfo
     * @param server_time
     */
    private void getCurrentTimeZoneOnAhead(MHomeSeckillTimeInfo mHomeSeckillTimeInfo, long server_time) {
        if (mHomeSeckillTimeInfo != null) {
            if (mHomeSeckillTimeInfo.kill_time_start.size() > 0) {
                if (mHomeSeckillTimeInfo.kill_time_end.size() > 0) {
                    if (mHomeSeckillTimeInfo.kill_time_start.get(0) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_end.get(0)) {
                        changeStartTime(0, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 0;
                    } else if (mHomeSeckillTimeInfo.kill_time_end.get(0) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_start.get(1)) {
                        changeRankTime(1, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 0;
                    } else if (mHomeSeckillTimeInfo.kill_time_start.get(1) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_end.get(1)) {
                        changeStartTime(1, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 1;
                    } else if (mHomeSeckillTimeInfo.kill_time_end.get(1) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_start.get(2)) {
                        changeRankTime(2, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 1;
                    } else if (mHomeSeckillTimeInfo.kill_time_start.get(2) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_end.get(2)) {
                        changeStartTime(2, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 2;
                    } else if (mHomeSeckillTimeInfo.kill_time_end.get(2) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_start.get(3)) {
                        changeRankTime(3, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 2;
                    } else if (mHomeSeckillTimeInfo.kill_time_start.get(3) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_end.get(3)) {
                        changeStartTime(3, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 3;
                    } else if (mHomeSeckillTimeInfo.kill_time_end.get(3) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_start.get(4)) {
                        changeRankTime(4, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 3;
                    } else if (mHomeSeckillTimeInfo.kill_time_start.get(4) <= server_time && server_time < mHomeSeckillTimeInfo.kill_time_end.get(4)) {
                        changeStartTime(4, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 4;
                    } else if (mHomeSeckillTimeInfo.kill_time_start.get(0) > server_time) {
                        changeRankTime(0, server_time, mHomeSeckillTimeInfo);
                        mCurrentSpace = 4;
                    } else if (mHomeSeckillTimeInfo.kill_time_end.get(4) <= server_time) {
                        mHeaderViewHolder.mTvSeckillHint.setText("今日秒杀已结束");
                        mHeaderViewHolder.mTimerDownView.setVisibility(View.GONE);
                        mCurrentSpace = 4;
                    }
                }
            }
        }
    }


    /**
     * 设置秒杀正在进行UI
     * @param space
     * @param server_time
     * @param mHomeSeckillTimeInfo
     */
    void changeStartTime(int space, Long server_time, MHomeSeckillTimeInfo mHomeSeckillTimeInfo) {
        Date date = new Date();
        date.setTime(mHomeSeckillTimeInfo.kill_time_start.get(space) * 1000);
        int hours = date.getHours();
        mHeaderViewHolder.mTvSeckillHint.setText(hours + "点场\t\t距结束\t");
        stratTimeDone(1, space, server_time * 1000, mHomeSeckillTimeInfo);
    }

    /**
     * 设置下一场秒杀UI
     * @param space
     * @param server_time
     * @param mHomeSeckillTimeInfo
     */
    void changeRankTime(int space, Long server_time, MHomeSeckillTimeInfo mHomeSeckillTimeInfo) {
        Date date = new Date();
        date.setTime(mHomeSeckillTimeInfo.kill_time_start.get(space) * 1000);
        int hours = date.getHours();
        mHeaderViewHolder.mTvSeckillHint.setText(hours + "点场\t\t距开始\t");
        stratTimeDone(0, space, server_time * 1000, mHomeSeckillTimeInfo);
    }

    /**
     * 倒计时
     *  @param isSeckill
     * @param zone
     * @param mHomeSeckillTimeInfo
     */
    private void stratTimeDone(final long isSeckill, final int zone, final long server_time, MHomeSeckillTimeInfo mHomeSeckillTimeInfo) {
        mHeaderViewHolder.mTimerDownView.setVisibility(View.VISIBLE);

        final long[] time = new long[1];
        if (isSeckill == 1) {
            time[0] = mHomeSeckillTimeInfo.kill_time_end.get(zone) * 1000 - server_time;
        } else {
            time[0] = mHomeSeckillTimeInfo.kill_time_start.get(zone) * 1000 - server_time;
        }
        mHeaderViewHolder.mTimerDownView.updateShow(time[0]);

        AsyncTask mExecute = new AsyncTask<Void, Long, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                while (time[0] > 0) {
                    try {
                        Thread.sleep(1000);
                        time[0] -= 1000;
                        publishProgress(time[0]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Long... values) {
                super.onProgressUpdate(values);
                mHeaderViewHolder.mTimerDownView.updateShow(values[0]);
                if (values[0] <= 1000) {
                    mPresenter.requestForSeckillTimes();
                }
            }
        }.execute();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> headerView 的 ViewHolder

    public class HeaderViewHolder {
        @BindView(R.id.mhome_banner)
        Banner mMhomeBanner;
        @BindView(R.id.iv_menu_00)
        Button mIvMenu00;
        @BindView(R.id.iv_menu_01)
        Button mIvMenu01;
        @BindView(R.id.iv_menu_02)
        Button mIvMenu02;
        @BindView(R.id.iv_menu_03)
        Button mIvMenu03;
        @BindView(R.id.iv_menu_04)
        Button mIvMenu04;
        @BindView(R.id.iv_menu_05)
        Button mIvMenu05;
        @BindView(R.id.iv_menu_06)
        Button mIvMenu06;
        @BindView(R.id.iv_menu_07)
        Button mIvMenu07;
        @BindView(R.id.iv_menu_08)
        Button mIvMenu08;
        @BindView(R.id.iv_menu_09)
        Button mIvMenu09;
        @BindView(R.id.imageView5)
        ImageView mImageView5;
        @BindView(R.id.new_account_left_btn)
        ImageView mNewAccountLeftBtn;
        @BindView(R.id.new_account_right_top_btn)
        ImageView mNewAccountRightTopBtn;
        @BindView(R.id.new_account_right_bottom_btn)
        ImageView mNewAccountRightBottomBtn;
        @BindView(R.id.iview)
        ImageView mIview;
        @BindView(R.id.iv_banner)
        ImageView mIvBanner;
        @BindView(R.id.tv_seckill_hint)
        TextView mTvSeckillHint;
        @BindView(R.id.timer_down_view)
        CountdownView mTimerDownView;
        @BindView(R.id.tv_more)
        TextView mTvMore;
        @BindView(R.id.recy_gallery)
        RecyclerView mRecyGallery;
        @BindView(R.id.gallery_layout)
        LinearLayout mGalleryLayout;
        @BindView(R.id.linear_root)
        LinearLayout mLinearRoot;
        @BindView(R.id.activity_main)
        RelativeLayout mActivityMain;

        public HeaderViewHolder(View headerView) {
            ButterKnife.bind(this, headerView);
            //轮播图
            mMhomeBanner = (Banner) headerView.findViewById(R.id.mhome_banner);
            mMhomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mMhomeBanner.setImageLoader(new GlideImageLoader());
            mMhomeBanner.setIndicatorGravity(BannerConfig.CENTER);
            //秒杀商品画廊
            mRecyGallery.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayout.HORIZONTAL, false));
            mGalleryAdapter = new GalleryAdapter(mActivity, R.layout.item_gallery);
            mRecyGallery.setAdapter(mGalleryAdapter);
        }

        @OnClick({R.id.iv_menu_00, R.id.iv_menu_01, R.id.iv_menu_02, R.id.iv_menu_03, R.id.iv_menu_04, R.id.iv_menu_05, R.id.iv_menu_06, R.id.iv_menu_07, R.id.iv_menu_08, R.id.iv_menu_09, R.id.new_account_left_btn, R.id.new_account_right_top_btn, R.id.new_account_right_bottom_btn, R.id.iv_banner, R.id.gallery_layout})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.iv_menu_00:
                    Toast.makeText(mActivity, "0", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_01:
                    Toast.makeText(mActivity, "1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_02:
                    Toast.makeText(mActivity, "2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_03:
                    Toast.makeText(mActivity, "3", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_04:
                    Toast.makeText(mActivity, "4", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_05:
                    Toast.makeText(mActivity, "5", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_06:
                    Toast.makeText(mActivity, "6", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_07:
                    Toast.makeText(mActivity, "7", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_08:
                    Toast.makeText(mActivity, "8", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_menu_09:
                    Toast.makeText(mActivity, "9", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.new_account_left_btn:
                    Toast.makeText(mActivity, "left_btn", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.new_account_right_top_btn:
                    Toast.makeText(mActivity, "right_top_btn", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.new_account_right_bottom_btn:
                    Toast.makeText(mActivity, "right_bottom_btn", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_banner:
                    break;
                case R.id.gallery_layout:
                    break;
            }
        }
    }
}
