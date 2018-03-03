package com.weiyi.mvpdemo.v.activity;

import android.os.Build;
import android.support.design.widget.BottomSheetDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.tab.Tabs;
import com.weiyi.mvpdemo.common.widget.TabFragmentHost;
import com.weiyi.mvpdemo.p.activity.MainPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Bind(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @Bind(android.R.id.tabs)
    TabWidget mTabs;
    @Bind(R.id.tabFragmentHost)
    TabFragmentHost mTabFragmentHost;
    private Tabs.Tab[] mMainTabs;

    private String [] mDatas = new String[]{"A", "B", "C", "D", "E", "F"};

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
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mActivity);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_bottom_sheet_share, null);
        initView(view);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }

    private void initView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        String string = getResources().getString(R.string.list_data);
        String[] split = string.split("_");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, android.R.id.text1, split);
        listView.setAdapter(arrayAdapter);
    }

}
