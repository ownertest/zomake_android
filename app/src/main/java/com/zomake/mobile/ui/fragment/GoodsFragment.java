package com.zomake.mobile.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.app.AppConstant;
import com.zomake.mobile.bean.AdvertImageBean;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.EmptyPresenter;
import com.zomake.mobile.ui.Presenter.MainCatalogPresenter;
import com.zomake.mobile.widget.SpacesItemDecoration;

import java.util.Arrays;

import butterknife.BindView;


public class GoodsFragment extends BaseFragment<EmptyPresenter>  {

    @BindView(R.id.tv_recyclerView)
    IRecyclerView recyclerView;
    private CommonRecycleViewAdapter<String> adapter;

    private String[] names = {"手机壳6／6s", "眼镜擦拭布"};
    private String[] prices = {"RMB 50", "RMB 35"};
    private Integer[] picIds = {R.drawable.for_test_car, R.drawable.for_test_car};


    @Override
    protected int getLayoutResource() {
        return R.layout.goods_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initGrid();
    }

    private void initGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CommonRecycleViewAdapter<String>(getContext(), R.layout.goods_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, String text) {
                helper.setText(R.id.name, text);
                helper.setText(R.id.price, prices[helper.getAdapterPosition() - 2]);
                helper.setBackgroundRes(R.id.pic, picIds[helper.getAdapterPosition() - 2]);
            }
        };
        adapter.addAll(Arrays.asList(names));
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapter(adapter);
    }
}
