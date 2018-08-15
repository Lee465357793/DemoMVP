package com.weiyi.mvpdemo.v.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.weiyi.mvpdemo.R;
import com.weiyi.mvpdemo.p.base.BasePresenter;
import com.weiyi.mvpdemo.v.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImgViewPagerActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private int[] drawableRes = new int[]{R.mipmap.bh, R.mipmap.cuteboy, R.mipmap.cutegirl, R.mipmap.hxy, R.mipmap.lly, R.mipmap.sf, R.mipmap.xkl};
    private int mIndex;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_img_view_pager;
    }

    @Override
    public BasePresenter getPresenter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
            getWindow().setSharedElementExitTransition(new ChangeImageTransform());
            getWindow().setSharedElementsUseOverlay(true);
        }
        return new BasePresenter();
    }

    @Override
    protected void onViewBinding() {
        mIndex = getIntent().getIntExtra("index", -1);
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView inflate = (ImageView) LayoutInflater.from(mActivity).inflate(R.layout.view_image, null);
                inflate.setImageResource(drawableRes[mIndex == -1 ? position%drawableRes.length : mIndex]);
                mIndex = -1;
                container.addView(inflate);
                return inflate;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        mViewPager.setAdapter(adapter);
    }
}
