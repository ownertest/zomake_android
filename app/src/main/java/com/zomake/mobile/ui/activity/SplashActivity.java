package com.zomake.mobile.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.jaydenxiao.common.base.BaseActivity;
import com.zomake.mobile.R;

import butterknife.Bind;

/**
 * des:启动页
 * Created by xsf
 * on 2016.09.15:16
 */
public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetTranslanteBar();
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1f);
        ObjectAnimator objectAnimator= ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.setDuration(2000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                MainActivity.startAction(SplashActivity.this);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimator.start();
    }

}
