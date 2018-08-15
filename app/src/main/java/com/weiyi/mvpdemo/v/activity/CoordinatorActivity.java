package com.weiyi.mvpdemo.v.activity;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.ActionMode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.activity.TableLayoutPresenter;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

public class CoordinatorActivity extends BaseActivity {

    private BottomSheetBehavior<View> mBottomSheetBehavior;

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


        final View shareView = findViewById(R.id.share_view);
        final View btn1 = findViewById(R.id.btn_1);
        final View btn2 = findViewById(R.id.btn_2);
        final View btn3 = findViewById(R.id.btn_3);
        final View btn4 = findViewById(R.id.btn_4);
        final View btn5 = findViewById(R.id.btn_5);
        final View btn6 = findViewById(R.id.btn_6);
        mBottomSheetBehavior = BottomSheetBehavior.from(shareView);
//        //设置bottomSheet 折叠时的高度
//        mBottomSheetBehavior.setPeekHeight(70);
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }

        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){


                   btn6.animate().setStartDelay(50 ).scaleYBy(1).scaleXBy(1).scaleX(0).scaleY(0).start();
                   btn5.animate().setStartDelay(100).scaleYBy(1).scaleXBy(1).scaleX(0).scaleY(0).start();
                   btn4.animate().setStartDelay(150).scaleYBy(1).scaleXBy(1).scaleX(0).scaleY(0).start();
                   btn3.animate().setStartDelay(200).scaleYBy(1).scaleXBy(1).scaleX(0).scaleY(0).start();
                   btn2.animate().setStartDelay(250).scaleYBy(1).scaleXBy(1).scaleX(0).scaleY(0).start();
                   btn1.animate().setStartDelay(300).scaleYBy(1).scaleXBy(1).scaleX(0).scaleY(0).setListener(new Animator.AnimatorListener() {
                       @Override
                       public void onAnimationStart(Animator animation) {

                       }

                       @Override
                       public void onAnimationEnd(Animator animation) {
                           mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                           mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                       }

                       @Override
                       public void onAnimationCancel(Animator animation) {

                       }

                       @Override
                       public void onAnimationRepeat(Animator animation) {

                       }
                   }).start();


               }else {
                   mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                   btn1.animate().setStartDelay(50 ).scaleXBy(0).scaleXBy(0).scaleX(1).scaleY(1).start();
                   btn2.animate().setStartDelay(100).scaleXBy(0).scaleXBy(0).scaleX(1).scaleY(1).start();
                   btn3.animate().setStartDelay(150).scaleXBy(0).scaleXBy(0).scaleX(1).scaleY(1).start();
                   btn4.animate().setStartDelay(200).scaleXBy(0).scaleXBy(0).scaleX(1).scaleY(1).start();
                   btn5.animate().setStartDelay(250).scaleXBy(0).scaleXBy(0).scaleX(1).scaleY(1).start();
                   btn6.animate().setStartDelay(300).scaleXBy(0).scaleXBy(0).scaleX(1).scaleY(1).start();
               }
            }
        });
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
