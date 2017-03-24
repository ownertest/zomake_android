package com.zomake.mobile.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
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
import com.zomake.mobile.ui.Presenter.MainCatalogPresenter;

import butterknife.BindView;

/**
 * Created by Ryan on 17/1/19.
 */
public class OrderListFragment extends BaseFragment<MainCatalogPresenter>  {


    @Override
    protected int getLayoutResource() {
        return R.layout.order_list_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}
