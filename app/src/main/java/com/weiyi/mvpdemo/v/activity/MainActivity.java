package com.weiyi.mvpdemo.v.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.adapter.BaseRecyclerAdapterX;
import com.weiyi.mvpdemo.common.callback.ItemTouchHelperCallBack;
import com.weiyi.mvpdemo.common.tab.Tabs;
import com.weiyi.mvpdemo.common.widget.BottomSheetDialogMy;
import com.weiyi.mvpdemo.common.widget.TabFragmentHost;
import com.weiyi.mvpdemo.p.activity.MainPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @BindView(android.R.id.tabs)
    TabWidget mTabs;
    @BindView(R.id.tabFragmentHost)
    TabFragmentHost mTabFragmentHost;
    private Tabs.Tab[] mMainTabs;

    private String [] mDatas = new String[]{"A", "B", "C", "D", "E", "F"};
    private BottomSheetDialog mBottomSheetDialog;
    private BottomSheetDialogMy mBottomSheetDialogErr;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onViewBinding() {
//        mToolbar.setTitle("主页");
//        setSupportActionBar(mToolbar);
//        mToolbar.setNavigationIcon(R.mipmap.icon_me);
//        mPresenter.request();
//        mBtnTablayout.getText();

        mMainTabs = Tabs.values();

        mTabFragmentHost.setup(mActivity, getSupportFragmentManager(), android.R.id.tabcontent);
        if (Build.VERSION.SDK_INT > 10){
            mTabFragmentHost.getTabWidget().setShowDividers(0);
        }

        for (int i = 0; i < mMainTabs.length; ++i) {
            TabHost.TabSpec tabSpec = mTabFragmentHost.newTabSpec(mMainTabs[i].tabName);
            TextView textView = new TextView(mActivity);
            textView.setGravity(Gravity.CENTER);
            textView.setText(mMainTabs[i].tabName);
            textView.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(mMainTabs[i].drawRes), null, null);
            tabSpec.setIndicator(textView);
            mTabFragmentHost.addTab(tabSpec, mMainTabs[i].clz, null);
        }

    }

    public void showBottomSheetDialog(){
        if (mBottomSheetDialog == null){

            mBottomSheetDialog = new BottomSheetDialog(mActivity);
            View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_bottom_sheet_share, null);
            initView(view);
            mBottomSheetDialog.setContentView(view);
            mBottomSheetDialog.setCancelable(true);
            mBottomSheetDialog.setCanceledOnTouchOutside(true);
            mBottomSheetDialog.show();

            View viewById = mBottomSheetDialog.getDelegate().findViewById(R.id.design_bottom_sheet);
            final BottomSheetBehavior from = BottomSheetBehavior.from(viewById);
            from.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        from.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        mBottomSheetDialog.dismiss();
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        }else {
            mBottomSheetDialog.show();
        }
    }
    public void showBottomSheetDialogErr(){
        if (mBottomSheetDialogErr == null){
            mBottomSheetDialogErr = new BottomSheetDialogMy(mActivity);
            View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_bottom_sheet_share, null);
            initView(view);
            mBottomSheetDialogErr.setContentView(view);
            mBottomSheetDialogErr.setCancelable(true);
            mBottomSheetDialogErr.setCanceledOnTouchOutside(true);
            mBottomSheetDialogErr.show();
        }else {
            mBottomSheetDialogErr.show();
            mBottomSheetDialogErr.mBehavior.setState(4);
        }
    }

    private void initView(final View view) {
        final String[] split = getResources().getStringArray(R.array.arrayListDatas);

        final ArrayList<String> listDatas= new ArrayList<>();
        for (String str : split) {
            listDatas.add(str);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        BaseRecyclerAdapterX adapter = new BaseRecyclerAdapterX(mActivity, R.layout.item_cardview) {

            @Override
            protected void _onBindViewHolder(final BaseViewHolder holder, int position, Object o) {
                holder.setText(R.id.item_text, listDatas.get(position));

                holder.setOnClick(R.id.item_text, new View.OnClickListener() {

                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            //                            startActivity(new Intent(mActivity, AnimActivity.class));
                            //                            startActivity(new Intent(mActivity, AnimActivity.class), ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle());
                            //                            startActivity(new Intent(mActivity, AnimActivity. ), ActivityOptions.makeCustomAnimation(mActivity, R.anim.anim_open_act_in, R.anim.anim_open_act_out).toBundle());
                            startActivity(new Intent(mActivity, AnimActivity.class), ActivityOptions.makeSceneTransitionAnimation(mActivity, holder.getView(R.id.public_iv), "public_img").toBundle());
                        }else {
                            startActivity(new Intent(mActivity, AnimActivity.class));
                            overridePendingTransition(R.anim.anim_open_act_in, R.anim.anim_open_act_out);
                        }
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.setListDatas(listDatas);
        ItemTouchHelperCallBack itemTouchHelperCallBack = new ItemTouchHelperCallBack(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);




    }

    public void onSuccess() {

    }
}
