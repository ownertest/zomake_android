package com.zomake.mobile.ui.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.bean.CatalogProductListBean;
import com.zomake.mobile.bean.CatalogProductListBean.DataEntity.ProductArrEntity;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.bean.ShopDetailBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.ShopInfoPresenter;

import butterknife.BindView;

/**
 * Created by wojiushiwn on 2017/3/25.
 * desc:
 */

public class ShopInfoFragment extends BaseFragment<ShopInfoPresenter> implements BaseContract.IShopInfoView {
    @BindView(R.id.recyclerView)
    IRecyclerView mRecyclerView;
    private CommonRecycleViewAdapter<ProductArrEntity> mAdapter;
    private CatalogProductListBean.DataEntity mProductBeanList;

    private String mShopId;

    @Override
    protected int getLayoutResource() {
        return R.layout.base_recylerview;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mShopId = getArguments().getString("shopId");
        }
        mRecyclerView.setRefreshEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter = new CommonRecycleViewAdapter<ProductArrEntity>(getContext(), R.layout.model_item_grid) {
            @Override
            public void convert(ViewHolderHelper helper, ProductArrEntity bean) {
                helper.setText(R.id.product_name, bean.getName());
                int price = bean.getAttachment().getBasePriceMap().getRMB()
                        + bean.getAttachment().getRoyaltyMap().getRMB();
                helper.setText(R.id.product_price, String.format("RMB %d", price));
                ImageLoaderUtils.display(getActivity(), helper.getView(R.id.image),
                        ImageLoaderUtils.DEFAULT_URL_HOST + bean.getCover());
            }
        };
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getProductList(mShopId);
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

    @Override
    public void showShopDetail(ShopDetailBean bean) {

    }

    @Override
    public void showProductList(CatalogProductListBean bean, boolean isRefresh) {
        if (isRefresh)
            mAdapter.set(bean.getData().getProductArr());
        else
            mAdapter.addAll(bean.getData().getProductArr());
    }

    @Override
    public void showCatalogList(MainCatalogBean bean) {

    }

}
