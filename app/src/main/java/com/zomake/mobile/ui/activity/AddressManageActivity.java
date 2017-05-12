package com.zomake.mobile.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.ui.Presenter.EmptyPresenter;
import com.zomake.mobile.widget.SpacesItemDecoration;

import java.util.Arrays;

import butterknife.BindView;

public class AddressManageActivity extends BaseActivity<EmptyPresenter> {

    @BindView(R.id.address_re)
    IRecyclerView recyclerView;

    private CommonRecycleViewAdapter<String> adapter;
    private String[] iconTexts = {"钱包", "收货地址", "优惠", "收件箱", "收藏", "反馈"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_manage;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initAddressList();
    }

    private void initAddressList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonRecycleViewAdapter<String>(this, R.layout.address_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, String text) {
                helper.setText(R.id.title, text);
            }
        };
        adapter.addAll(Arrays.asList(iconTexts));
        recyclerView.setAdapter(adapter);
    }
}
