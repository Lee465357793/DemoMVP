package com.weiyi.mvpdemo.v.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.activity.MainPresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity<MainPresenter> {

    @BindView(R.id.btn_tablayout)
    Button mBtnTablayout;
    @BindView(R.id.anim_view)
    ImageView mAnimView;
    @BindView(R.id.text_view)
    ImageView mTextView;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onViewBinding() {
        mToolbar.setTitle("主页");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.icon_me);
        mPresenter.request();
        mBtnTablayout.getText();
//沉浸式
//        View decorView = getWindow().getDecorView();
//        if (Build.VERSION.SDK_INT > 21){
//
////            View.SYSTEM_UI_FLAG_FULLSCREEN //全屏展示（隐藏 StatusBar 、 ActionBar）
////            getWindow().setStatusBarColor(Color.TRANSPARENT);
////            getSupportActionBar().hide(); //这里  actionBar 通  ToolBar
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    |View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                Random random = new Random();
                themeIndex = random.nextInt(5);
                Intent intent = getIntent();
                finish();//结束当前的Activity
                overridePendingTransition(0,0);//不要动画
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.text_view, R.id.btn_tablayout, R.id.btn_toolBar, R.id.btn_coordinator})
    public void onViewClicked(View view) {
        mPresenter.request();
//        startActivity(new Intent(this, TabLayoutActivity.class));

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

                final ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                ScaleAnimation scaleAnimation2 = new ScaleAnimation(1f, 0.8f, 1f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(150);
                scaleAnimation2.setDuration(150);
                scaleAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mAnimView.startAnimation(scaleAnimation);
                        mAnimView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mTextView.startAnimation(scaleAnimation2);
                mTextView.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_tablayout:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.btn_toolBar:
                startActivity(new Intent(this, ToolbarForActionBarActivity.class));
                break;
            case R.id.btn_coordinator:
                startActivity(new Intent(this, CoordinatorActivity.class));
                break;
        }

    }
}
