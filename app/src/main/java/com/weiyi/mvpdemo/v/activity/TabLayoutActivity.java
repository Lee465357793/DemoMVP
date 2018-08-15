package com.weiyi.mvpdemo.v.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.common.adapter.RecyViewHolder;
import com.weiyi.mvpdemo.p.activity.TableLayoutPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import butterknife.BindView;


public class TabLayoutActivity extends BaseActivity<TableLayoutPresenter> {
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    public TableLayoutPresenter getPresenter() {
        return new TableLayoutPresenter();
    }

    @Override
    protected void onViewBinding() {
        mToolbar.setTitle("主页");
        mToolbar.setNavigationIcon(R.mipmap.icon_me);
//        mToolbar.setContentInsetStartWithNavigation(0);
        mToolbar.inflateMenu(R.menu.toolbar_menu);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        final String[] split = getResources().getStringArray(R.array.arrayListDatas);

        RecyclerView listView = (RecyclerView) findViewById(R.id.listview);

        listView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<ViewHolder>() {

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = LayoutInflater.from(mActivity).inflate(R.layout.item_cardview, parent, false);
                ViewHolder viewHolder = new ViewHolder(inflate);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.mTextView.setText(split[position]);
            }

            @Override
            public int getItemCount() {
                return split.length;
            }
        };
        listView.setAdapter(adapter);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected final TextView mTextView;
        public ImageView mPublicIv;

        public ViewHolder(View inflate) {
            super(inflate);
            mTextView = (TextView) inflate.findViewById(R.id.item_text);
            mPublicIv = (ImageView) inflate.findViewById(R.id.public_iv);
        }
    }
}
