package com.zomake.mobile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.zomake.mobile.R;
import com.zomake.mobile.ui.Presenter.EmptyPresenter;
import com.zomake.mobile.ui.fragment.LoginFragment;
import com.zomake.mobile.ui.fragment.RegisterFragment;
import com.zomake.mobile.ui.fragment.ShopInfoFragment;
import com.zomake.mobile.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class LoginRegisterActivity extends BaseActivity<EmptyPresenter> {

    @BindView(R.id.tv_back)
    FontTextView mTvBack;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private String[] mTabsNameArr = {"登录", "注册"};
    private BaseFragmentAdapter fragmentAdapter;


    @Override
    protected boolean isShowTitleBar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        List<String> tabNames = new ArrayList<>();
        List<Fragment> mFragmentList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            tabNames.add(mTabsNameArr[i]);
            Fragment fragment = i == 0 ? createLoginFragments() : createRegisterFragments();
            mFragmentList.add(fragment);
        }
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragmentList, tabNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), mFragmentList, tabNames);
        }

        mViewPager.setAdapter(fragmentAdapter);
        mTabs.setupWithViewPager(mViewPager);
        MyUtils.dynamicSetTabLayoutMode(mTabs);
    }

    private LoginFragment createLoginFragments() {
        return new LoginFragment();
    }

    private RegisterFragment createRegisterFragments() {
        return new RegisterFragment();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        context.startActivity(intent);
    }
}
