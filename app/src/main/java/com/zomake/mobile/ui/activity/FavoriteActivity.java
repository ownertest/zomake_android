package com.zomake.mobile.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.ui.fragment.GoodsFragment;
import com.zomake.mobile.ui.fragment.StoreFragment;
import com.zomake.mobile.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FavoriteActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    private String[] orderText = {"商品", "店铺"};
    private BaseFragmentAdapter fragmentAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment();
    }

    private void initFragment() {
        List<String> channelNames = new ArrayList<>();
        List<Fragment> mNewsFragmentList = new ArrayList<>();
        channelNames.add(orderText[0]);
        GoodsFragment fragment = new GoodsFragment();
        Bundle bundle = new Bundle();
        //bundle.putSerializable(AppConstant.CATALOG_MENU, newsChannel);
        fragment.setArguments(bundle);
        mNewsFragmentList.add(fragment);
        channelNames.add(orderText[1]);
        StoreFragment storeFragment = new StoreFragment();
        bundle = new Bundle();
        //bundle.putSerializable(AppConstant.CATALOG_MENU, newsChannel);
        storeFragment.setArguments(bundle);
        mNewsFragmentList.add(storeFragment);

        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), mNewsFragmentList, channelNames);
        }
        viewPager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewPager);
        MyUtils.dynamicSetTabLayoutMode(tabs);
    }
}
