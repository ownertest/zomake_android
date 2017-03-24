package com.zomake.mobile.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.zomake.mobile.R;
import com.zomake.mobile.ui.activity.AddressManageActivity;
import com.zomake.mobile.ui.activity.CarActivity;
import com.zomake.mobile.ui.activity.CatalogProductListActivity;
import com.zomake.mobile.ui.activity.FavoriteActivity;
import com.zomake.mobile.ui.activity.InboxActivity;
import com.zomake.mobile.ui.activity.OrderActivity;
import com.zomake.mobile.ui.activity.ProductDetailActivity;
import com.zomake.mobile.ui.activity.SettingActivity;
import com.zomake.mobile.ui.activity.WalletActivity;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.zomake.mobile.widget.SpacesItemDecoration;

import java.util.Arrays;
import butterknife.BindView;



public class MyFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.user_center_recycler)
    IRecyclerView recyclerView;
    @BindView(R.id.order)
    LinearLayout ll_order;
    @BindView(R.id.car)
    LinearLayout ll_car;
    @BindView(R.id.user_center_set)
    FontTextView ft_set;

    private String[] iconIds = {"\uEA3B", "\ue794", "\ue7ac", "\ueac9", "\ue6df", "\ue6a4"};
    private String[] iconTexts = {"钱包", "收货地址", "优惠", "收件箱", "收藏", "反馈"};
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
                helper.setText(R.id.ftv_icon, iconIds[helper.getAdapterPosition() - 2]);
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.e("123", position + "");
                if (position == 2) {
                    Intent intent = new Intent(getActivity(), WalletActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getActivity(), AddressManageActivity.class);
                    startActivity(intent);
                } else if (position == 4) {
//                    Intent intent = new Intent(getActivity(), CarActivity.class);
                    Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(getActivity(), InboxActivity.class);
                    startActivity(intent);
                } else if (position == 6) {
//                    Intent intent = new Intent(getActivity(), FavoriteActivity.class);
                    Intent intent = new Intent(getActivity(), CatalogProductListActivity.class);
                    intent.putExtra("catalogId", "5836c796d939c40001dc842c");
                    intent.putExtra("catalogName", "手机壳");
                    intent.putExtra("parentName", "数码");
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        adapter.addAll(Arrays.asList(iconTexts));
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.order) {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.car) {
            Intent intent = new Intent(getActivity(), CarActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.user_center_set) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
    }
}
