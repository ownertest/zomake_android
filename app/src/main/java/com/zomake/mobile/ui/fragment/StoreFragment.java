package com.zomake.mobile.ui.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.ui.Presenter.EmptyPresenter;
import com.zomake.mobile.widget.SpacesItemDecoration;

import java.util.Arrays;

import butterknife.BindView;


public class StoreFragment extends BaseFragment<EmptyPresenter>  {

    @BindView(R.id.tv_recyclerView)
    IRecyclerView recyclerView;
    private CommonRecycleViewAdapter<String> adapter;

    private String[] names = {"店铺1", "店铺2"};
    private String[] intro = {"这是店铺1", "这是店铺2"};
    private Integer[] pic = {R.drawable.user_info_default_banner, R.drawable.user_info_default_banner};
    private Integer[] avatar = {R.drawable.store_avatar, R.drawable.store_avatar};



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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CommonRecycleViewAdapter<String>(getContext(), R.layout.store_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, String text) {
                helper.setText(R.id.name, text);
                helper.setText(R.id.intro, intro[helper.getAdapterPosition() - 2]);
                helper.setBackgroundRes(R.id.pic, pic[helper.getAdapterPosition() - 2]);
                helper.setBackgroundRes(R.id.avatar, avatar[helper.getAdapterPosition() - 2]);
            }
        };
        adapter.addAll(Arrays.asList(names));
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapter(adapter);
    }
}
