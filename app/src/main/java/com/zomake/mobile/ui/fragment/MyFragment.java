package com.zomake.mobile.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.ui.Presenter.MainShopPresenter;
import com.zomake.mobile.widget.SpacesItemDecoration;

import java.util.Arrays;
import butterknife.Bind;

/**
 * Created by Ryan on 17/1/18.
 */

public class MyFragment extends BaseFragment {

    @Bind(R.id.user_center_recycler)
    IRecyclerView recyclerView;

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
}
