package com.weiyi.mvpdemo.v.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.adapter.PinnedRecyAdapter;
import com.weiyi.mvpdemo.m.bean.Cart;
import com.weiyi.mvpdemo.m.bean.CartDataBean;
import com.weiyi.mvpdemo.m.bean.ProductInfo;
import com.weiyi.mvpdemo.p.CarFragmentPst;
import com.weiyi.mvpdemo.p.base.ShopCartRes;
import com.weiyi.mvpdemo.v.MainActivity;
import com.weiyi.mvpdemo.v.base.BaseFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by Lee on 2017/6/10.
 */
public class CarFragment extends BaseFragment<MainActivity, CarFragmentPst> implements OnRefreshListener {

    @Bind(R.id.recy_cart_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.cb_check_all)
    CheckBox mCbCheckAll;
    @Bind(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @Bind(R.id.tv_delete)
    TextView mTvDelete;
    @Bind(R.id.tv_bug_pay)
    TextView mTvBugPay;
    @Bind(R.id.ll_bottom_layout)
    LinearLayout mLlBottomLayout;
    @Bind(R.id.smart_cart_refresh)
    SmartRefreshLayout mSmartCartRefresh;
    //分别定义三种显示格式的值
    public static int GridFlag = 1;
    public static int ListFlag = 2;
    public static int titleFlag = 3;
    public static int ListHintFlag = 4;

    private List<CartDataBean> mDatas = new ArrayList();
    private SparseArray<Integer> groups = new SparseArray();
    private PinnedRecyAdapter pAdapter;
    private GridLayoutManager gManager;
    private double totalPrice;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    DecimalFormat DF = new DecimalFormat("#########0.00");

    private List<CartDataBean> mCartDatas = new ArrayList<>();
    private List<CartDataBean> mGoodsDatas = new ArrayList<>();

    private boolean showToast;
    private boolean isKnow = false;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSmartCartRefresh.setOnRefreshListener(this);
        mSmartCartRefresh.setEnableLoadmore(false);
        mSmartCartRefresh.setRefreshHeader(new ClassicsHeader(mActivity), -1, -2);
        mPresenter.requestGoodsListData();
        mPresenter.requestCartData();
        mCbCheckAll.setChecked(true);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public CarFragmentPst getPresenter() {
        return new CarFragmentPst();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        /**
         * 在这里，我们把LayoutManager设成Grid形式，根据实际情况，这里我设置成三列
         * setSpanSizeLookup方法，个人感觉有点像我们xml布局中的layout_weight,它表示Grid中的每项占几个位置
         * 在这里，根据实际情况，我们可以得知，类别栏和下面的list形式数据全是每条占满的也就是一个占三个的位置
         */
        gManager = new GridLayoutManager(mActivity, 2);
        gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= groups.get(1)) { //第一组的截止positon
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gManager);
        pAdapter = new PinnedRecyAdapter(null, mActivity);
        mRecyclerView.setAdapter(pAdapter);
        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        pAdapter.setOnItemClickLitener(new PinnedRecyAdapter.OnItemClickLitener() {

            @Override
            public void OnItemClick(View view, int positon, int type) {
                Toast.makeText(mActivity, String.valueOf(positon), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
            }

            @Override
            public void onCheckedChangeListener(boolean isChecked, int position) {
                CartDataBean productInfo = pAdapter.getList_data().get(position);
                if (isChecked){ //选中
                    Double goodsPrice = Double.parseDouble(productInfo.goods_price) * productInfo.count;
                    totalPrice += goodsPrice;
                    totalCount ++;
                }else{
                    Double goodsPrice = Double.parseDouble(productInfo.goods_price) * productInfo.count;
                    totalPrice -=goodsPrice;
                    totalCount --;
                }
                mTvTotalPrice.setText(DF.format(totalPrice));
                mTvBugPay.setText("去支付(" + totalCount + ")");
                if (totalCount +1 == groups.get(1)){
                    pAdapter.getList_data().get(0).is_checked = (true);
                    mCbCheckAll.setChecked(true);
                    pAdapter.notifyItemChanged(0);
                }
            }

            @Override
            public void onAllCheckedChangeListener(boolean isChecked) {
                checkAll(isChecked);
            }

            @Override
            public void onGoodsNumberChanges(int position, boolean isChecked) {
                totalPrice = 0.00;
                totalCount = 0;

                for (CartDataBean obj : pAdapter.getList_data()) {
                    if (obj.type == ListFlag){
                        obj.is_checked = (isChecked);
                        Double goodsPrice = Double.parseDouble(obj.goods_price) * obj.count;
                        if (isChecked){
                            totalPrice +=goodsPrice;
                            totalCount ++;
                        }
                    }
                }

                mTvTotalPrice.setText(DF.format(totalPrice));
                mTvBugPay.setText("去支付(" + totalCount + ")");
            }

            @Override
            public void onAddCartSuccess() {
                showToast = true;
            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(-1)) {
                    if (showToast && !isKnow){
                        isKnow = true;
                        Toast toast = Toast.makeText(mActivity, "没找到购物车商品?下拉刷新看看(*^▽^*)", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        mCbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pAdapter.getList_data().get(0).is_checked = (isChecked);
                checkAll(isChecked);
                pAdapter.notifyItemChanged(0);
            }
        });
    }


    /**
     * 初始化数据
     * 因为这是Grid与list的混排，所以没法将title也就是类别，放到每条记录里，因为Grid类型，没法处理。
     * 所以这里我采用的是单独把类别做为一个条记录存入并且给了一个单独的类别3
     *
     * @param cartList  购物车数据
     * @param goodsList 推荐商品数据
     */
    void jointData(List<CartDataBean> cartList, List<CartDataBean> goodsList) {
        mDatas.clear();
        groups.clear();
        //========拼接 购物车数据
        if (cartList.size() <= 0) {
            CartDataBean productInfo = new CartDataBean();
            productInfo.pinned_title = ("hint");
            productInfo.type = (ListHintFlag);
            productInfo.sordid = (1);
            mDatas.add(productInfo);
        } else {
            CartDataBean productInfo = new CartDataBean();
            productInfo.pinned_title = ("乐拉商城");
            productInfo.type = (titleFlag);
            productInfo.is_checked = (true);
            productInfo.sordid = (1);
            mDatas.add(productInfo);
        }
        if (mCartDatas.size() > 0) {
            mDatas.addAll(mCartDatas);
        }
        groups.put(1, mDatas.size());

        //========拼接 推荐商品数据
        CartDataBean productInfo = new CartDataBean();
        productInfo.pinned_title = ("为您推荐");
        productInfo.type = (titleFlag);
        productInfo.sordid = (2);
        mDatas.add(productInfo);

        if (mGoodsDatas.size() > 0) {
            mDatas.addAll(mGoodsDatas);
        }

        groups.put(2, mDatas.size());
        pAdapter.setList_data(mDatas);
    }

    /**
     * 全选 or 反选
     * @param isChecked
     */
    private void checkAll(boolean isChecked) {
        totalCount = 0;
        totalPrice = 0.00;
        int i = 1;
        //便利 所有购物车商品 修改选中状态， //TODO 单个条目刷新 暂时解决条目 闪屏问题
        for (CartDataBean obj : pAdapter.getList_data()) {
            if (obj.type == ListFlag){
                obj.is_checked = (isChecked);
                Double goodsPrice = Double.parseDouble(obj.goods_price) * obj.count;
                if (isChecked){
                    totalPrice +=goodsPrice;
                    totalCount ++;
                    pAdapter.notifyItemChanged(i);
                }else{
                    pAdapter.notifyItemChanged(i);
                }
                i++;
            }
        }
        mCbCheckAll.setChecked(isChecked);
        //                pAdapter.notifyItemRangeChanged(0,groups.get(1));

        if (isChecked){ //全部选中
            mTvTotalPrice.setText(DF.format(totalPrice));
            mTvBugPay.setText("去支付(" + totalCount + ")");
        }else{//全部未选中
            mTvTotalPrice.setText("0.00");
            mTvBugPay.setText("去支付(0)");
        }
    }

    @OnClick({R.id.tv_delete, R.id.tv_bug_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                Toast.makeText(mActivity, "delete", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_bug_pay:
                Toast.makeText(mActivity, "create_order", Toast.LENGTH_LONG).show();
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    public void responseSuccessForGoodsList(LinkedList<CartDataBean> mGoodsInfo) {
        mGoodsDatas = mGoodsInfo;
        jointData(mCartDatas, mGoodsDatas);
    }

    public void responseSuccessForCarDatas(ShopCartRes shopCartRes) {
        mCartDatas.clear();
        totalCount = 0;
        totalPrice = 0.00;
        for (Cart obj : shopCartRes.data) {
            CartDataBean productInfo = new CartDataBean();
            productInfo.is_checked = true;
            productInfo.goods_name = obj.goods_name;
            productInfo.goods_desc = obj.goods_attr;
            productInfo.goods_thumb = obj.goods_thumb;
            productInfo.count = 1;
            productInfo.goods_id = obj.goods_id;
            productInfo.goods_price = "1";
            productInfo.pinned_title = "乐拉商城";
            productInfo.type = ListFlag;
            totalCount++;
            totalPrice += 1;
            mCartDatas.add(productInfo);
        }
        if (mCartDatas.size() > 0){
            mLlBottomLayout.setVisibility(View.VISIBLE);
        }else{
            mLlBottomLayout.setVisibility(View.GONE);
        }
        mTvTotalPrice.setText(DF.format(totalPrice));
        mTvBugPay.setText("去支付(" + totalCount + ")");
        jointData(mCartDatas, mGoodsDatas);
    }
}
