package com.zomake.mobile.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.app.AppConstant;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.MainShopPresenter;
import com.zomake.mobile.utils.MyUtils;
import com.zomake.mobile.widget.FontTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Ryan on 17/1/18.
 */

public class ShopFragment extends BaseFragment<MainShopPresenter> implements BaseContract.MainView {
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.add_channel_iv)
    FontTextView addChannelIv;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.base_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        mPresenter.getMainChannels();
    }

    @Override
    public void showMainChannels(MainCatalogBean mainCatalogBeen) {
        if (mainCatalogBeen != null) {
            List<String> channelNames = new ArrayList<>();
            List<Fragment> mNewsFragmentList = new ArrayList<>();
            for (int i = 0; i < mainCatalogBeen.data.size(); i++) {
                channelNames.add(mainCatalogBeen.data.get(i).name);
                mNewsFragmentList.add(createListFragments(mainCatalogBeen.data.get(i).attachment));
            }
            if (fragmentAdapter == null) {
                fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
            } else {
                //刷新fragment
                fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
            }
            viewPager.setAdapter(fragmentAdapter);
            tabs.setupWithViewPager(viewPager);
            MyUtils.dynamicSetTabLayoutMode(tabs);
        }
    }

    private CatalogFragment createListFragments(MainCatalogBean.DataBean.AttachmentBeanXX newsChannel) {
        CatalogFragment fragment = new CatalogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.CATALOG_MENU, newsChannel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
