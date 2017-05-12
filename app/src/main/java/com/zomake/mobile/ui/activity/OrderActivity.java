package com.zomake.mobile.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.app.AppConstant;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.ui.Presenter.EmptyPresenter;
import com.zomake.mobile.ui.fragment.CatalogFragment;
import com.zomake.mobile.ui.fragment.OrderListFragment;
import com.zomake.mobile.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderActivity extends BaseActivity<EmptyPresenter> {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    private String[] orderText = {"全部", "待付款", "待发货", "待收货", "待评价", "售后中"};
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
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
        for (int i = 0; i < orderText.length; i++) {
            channelNames.add(orderText[i]);
            mNewsFragmentList.add(createListFragments());
        }
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

    private OrderListFragment createListFragments() {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        //bundle.putSerializable(AppConstant.CATALOG_MENU, newsChannel);
        fragment.setArguments(bundle);
        return fragment;
    }

}
