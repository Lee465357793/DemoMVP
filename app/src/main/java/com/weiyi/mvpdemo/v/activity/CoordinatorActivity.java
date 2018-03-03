package com.weiyi.mvpdemo.v.activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.activity.TableLayoutPresenter;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

public class CoordinatorActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_coordinator;
    }

    @Override
    public BasePresenter getPresenter() {
        return new TableLayoutPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ac_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String result = "";
        switch (item.getItemId()) {
            case R.id.ac_toolbar_copy:
                result = "Copy";
                break;
            case R.id.ac_toolbar_cut:
                result = "Cut";
                break;
            case R.id.ac_toolbar_del:
                result = "Del";
                break;
            case R.id.ac_toolbar_edit:
                result = "Edit";
                break;
            case R.id.ac_toolbar_email:
                result = "Email";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


    void switchActionMode(){
        startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuItem oneMenuItem = menu.add("one").setIcon(R.mipmap.icon_big_star_on);
                MenuItemCompat.setShowAsAction(oneMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
                MenuItem twoMenuItem = menu.add("two").setIcon(R.mipmap.icon_big_star_on);
                MenuItemCompat.setShowAsAction(twoMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
                MenuItem threeMenuItem = menu.add("three").setIcon(R.mipmap.icon_big_star_on);
                MenuItemCompat.setShowAsAction(threeMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
                return true; //使生效
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Toast.makeText(mActivity, item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    @Override
    protected void onViewBinding() {

//        AppBarLayout.ScrollingViewBehavior scrollingViewBehavior = new AppBarLayout.ScrollingViewBehavior();
//        ViewGroup.LayoutParams layoutParams = mNestedScrillView.getLayoutParams();
//        BottomSheetBehavior<NestedScrollView> from = BottomSheetBehavior.from(mNestedScrillView);
//        from.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//
//            }
//
//            @Override

//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });

    }
}
