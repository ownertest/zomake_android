package com.zomake.mobile.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.ui.Presenter.MainShopPresenter;
import com.zomake.mobile.ui.activity.SettingActivity;
import com.zomake.mobile.widget.FontTextView;
import com.zomake.mobile.widget.SpacesItemDecoration;

import java.util.Arrays;
import butterknife.Bind;

/**
 * Created by Ryan on 17/1/18.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.user_center_recycler)
    IRecyclerView recyclerView;
    @Bind(R.id.order)
    LinearLayout ll_order;
    @Bind(R.id.car)
    LinearLayout ll_car;
    @Bind(R.id.user_center_set)
    FontTextView ft_set;

    private Integer[] iconIds = {R.drawable.tab_bar_my_selected, R.drawable.tab_bar_my_selected, R.drawable.tab_bar_my_selected, R.drawable.tab_bar_my_selected, R.drawable.tab_bar_my_selected, R.drawable.tab_bar_my_selected};
    private String[] iconTexts = {"钱包", "收益", "优惠", "足迹", "收藏", "礼物"};
    private CommonRecycleViewAdapter<String> adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.my_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initFunctionGrid();
        ll_order.setOnClickListener(this);
        ll_car.setOnClickListener(this);
        ft_set.setOnClickListener(this);
    }

    private void initFunctionGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CommonRecycleViewAdapter<String>(getContext(), R.layout.user_center_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, String text) {
                helper.setText(R.id.name, text);
                helper.setBackgroundRes(R.id.image, iconIds[helper.getAdapterPosition() - 2]);
            }
        };
        adapter.addAll(Arrays.asList(iconTexts));
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.order) {

        } else if (v.getId() == R.id.car) {

        } else if (v.getId() == R.id.user_center_set) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
    }
}
