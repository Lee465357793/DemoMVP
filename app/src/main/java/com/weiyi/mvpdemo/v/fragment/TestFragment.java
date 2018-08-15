package com.weiyi.mvpdemo.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.v.activity.CoordinatorActivity;
import com.weiyi.mvpdemo.v.activity.MainActivity;
import com.weiyi.mvpdemo.v.activity.MenuActivity;
import com.weiyi.mvpdemo.v.activity.TabLayoutActivity;
import com.weiyi.mvpdemo.v.activity.ToolbarForActionBarActivity;
import com.weiyi.mvpdemo.v.base.BaseFragment;

import java.util.Observable;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.weiyi.mvpdemo.v.base.BaseActivity.themeIndex;

/**
 * Created by Lee on 2018/2/28 0028.
 */

public class TestFragment extends BaseFragment<MainActivity, BasePresenter> {

    @BindView(R.id.btn_tablayout)
    Button mBtnTablayout;
    @BindView(R.id.anim_view)
    ImageView mAnimView;
    @BindView(R.id.text_view)
    ImageView mTextView;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public BasePresenter getPresenter() {
        return new BasePresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar.setTitle("主页");
        mToolbar.setNavigationIcon(R.mipmap.icon_me);
        mToolbar.inflateMenu(R.menu.menu_main);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_search:
                        Random random = new Random();
                        themeIndex = random.nextInt(5);
                        Intent intent = mActivity.getIntent();
                        mActivity.finish();//结束当前的Activity
                        mActivity.overridePendingTransition(0,0);//不要动画
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "NavigationOnClick", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.btn_menu, R.id.btn_show_bottom_dialog_err, R.id.text_view, R.id.anim_view, R.id.btn_tablayout, R.id.btn_toolBar, R.id.btn_coordinator, R.id.btn_show_bottom_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.text_view:
                //                mAnimView.animate().scaleX(1f).scaleY(1f).setDuration(500).start();
                //开启一个调度作业
                //                JobInfo job = new JobInfo.Builder(0, new ComponentName(getApplicationContext(), TabLayoutActivity.class))
                //                        .setPeriodic(1000)
                //                        .setRequiresDeviceIdle(true)
                //                        .build();
                //
                //                JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                //                if (scheduler.getAllPendingJobs().size() == 0)
                //                    Log.wtf("测试", scheduler.schedule(job) == JobScheduler.RESULT_SUCCESS
                //                            ? "LookForMediaJob scheduled successfully!" : "LookForMediaJob scheduled failed!");

                starPre();
                break;
            case R.id.anim_view:
                starNor();
                break;
            case R.id.btn_menu:
                startActivity(new Intent(mActivity, MenuActivity.class));
                break;
            case R.id.btn_tablayout:
                startActivity(new Intent(mActivity, TabLayoutActivity.class));
                break;
            case R.id.btn_toolBar:
                startActivity(new Intent(mActivity, ToolbarForActionBarActivity.class));
                break;
            case R.id.btn_coordinator:
                startActivity(new Intent(mActivity, CoordinatorActivity.class));
                break;
            case R.id.btn_show_bottom_dialog:
                mActivity.showBottomSheetDialog();
                break;
            case R.id.btn_show_bottom_dialog_err:
                mActivity.showBottomSheetDialogErr();
                break;
        }

    }

    private void starPre() {
        final ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(150);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTextView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mAnimView.startAnimation(scaleAnimation);
        mAnimView.setVisibility(View.VISIBLE);
    }
    private void starNor() {
        final ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(150);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mTextView.setVisibility(View.VISIBLE);
        mAnimView.startAnimation(scaleAnimation);
        mAnimView.setVisibility(View.INVISIBLE);
    }
}
