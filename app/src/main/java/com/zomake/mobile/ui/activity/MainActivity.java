package com.zomake.mobile.ui.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpOptions;
import com.zomake.mobile.R;
import com.zomake.mobile.app.AppConstant;
import com.zomake.mobile.bean.TabEntity;
import com.zomake.mobile.ui.fragment.CustomFragment;
import com.zomake.mobile.ui.fragment.MyFragment;
import com.zomake.mobile.ui.fragment.ShopFragment;

import java.util.ArrayList;

import butterknife.BindView;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles = {"商店", "定制", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.tab_bar_shop, R.drawable.tab_bar_custom, R.drawable.tab_bar_my};
    private int[] mIconSelectIds = {
            R.drawable.tab_bar_shop, R.drawable.tab_bar_custom, R.drawable.tab_bar_my};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private static int tabLayoutHeight;
    private MyFragment myFragment;
    private ShopFragment shopFragment;
    private CustomFragment customFragment;


    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                com.jaydenxiao.common.R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isShowTitleBar() {
        return false;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        Acp.getInstance(this)
                .request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE)
                        .build(), null);
        //初始化菜单
        initTab();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
        tabLayout.measure(0, 0);
        tabLayoutHeight = tabLayout.getMeasuredHeight();
        //监听菜单显示或隐藏
        mRxManager.on(AppConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {

            @Override
            public void call(Boolean hideOrShow) {
                startAnimation(hideOrShow);
            }
        });

    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            shopFragment = (ShopFragment) getSupportFragmentManager().findFragmentByTag("shopFragment");
            customFragment = (CustomFragment) getSupportFragmentManager().findFragmentByTag("customFragment");
            myFragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("myFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            shopFragment = new ShopFragment();
            customFragment = new CustomFragment();
            myFragment = new MyFragment();

            transaction.add(R.id.fl_body, shopFragment, "shopFragment");
            transaction.add(R.id.fl_body, customFragment, "customFragment");
            transaction.add(R.id.fl_body, myFragment, "myFragment");
        }
        transaction.commit();
        switchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void switchTo(int position) {
        LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                transaction.hide(customFragment);
                transaction.hide(myFragment);
                transaction.show(shopFragment);
                transaction.commitAllowingStateLoss();
                break;
            //定制
            case 1:
                transaction.hide(shopFragment);
                transaction.hide(myFragment);
                transaction.show(customFragment);
                transaction.commitAllowingStateLoss();
                break;
            //我的
            case 2:
                transaction.hide(shopFragment);
                transaction.hide(customFragment);
                transaction.show(myFragment);
                transaction.commitAllowingStateLoss();
                myFragment.setUserInfoView();
                break;
            default:
                break;
        }
    }

    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide) {
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if (!showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(valueAnimator1 -> {
            layoutParams.height = (int) valueAnimator1.getAnimatedValue();
            tabLayout.setLayoutParams(layoutParams);
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator, alpha);
        animatorSet.start();
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
