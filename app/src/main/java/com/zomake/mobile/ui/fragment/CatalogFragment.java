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


import butterknife.Bind;


public class CatalogFragment extends BaseFragment<MainCatalogPresenter> implements BaseContract.CatalogView {
    @Bind(R.id.tv_recyclerView)
    IRecyclerView recyclerView;
    private CommonRecycleViewAdapter<MainCatalogBean.DataBean.AttachmentBeanXX.ChildrenBean> adapter;
    private MainCatalogBean.DataBean.AttachmentBeanXX childrenBeanList;
    private LinearLayout headerView;

    @Override
    protected int getLayoutResource() {
        return R.layout.catalog_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            childrenBeanList = (MainCatalogBean.DataBean.AttachmentBeanXX) getArguments().getSerializable(AppConstant.CATALOG_MENU);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        adapter = new CommonRecycleViewAdapter<MainCatalogBean.DataBean.AttachmentBeanXX.ChildrenBean>(getContext(), R.layout.catalog_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, MainCatalogBean.DataBean.AttachmentBeanXX.ChildrenBean productsDataBean) {
                helper.setText(R.id.name, productsDataBean.name);
                Glide.with(getActivity()).load("https://shop-cdn.zomake.com/" + productsDataBean.banner).into((ImageView) helper.getView(R.id.image));
            }
        };
        headerView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.advert_item_layout, recyclerView, false);
        recyclerView.setAdapter(adapter);
        recyclerView.addFooterView(headerView);
        adapter.addAll(childrenBeanList.getChildren());
        mPresenter.getCatalogList("57d5564f3916c325f8e9920e");
        mPresenter.getAdvertList("57d5564f3916c325f8e9924a");
    }

    @Override
    public void showCatalogList(CatalogProductsBean productsBean) {
    }

    @Override
    public void showAdvertList(AdvertImageBean imageBean) {
        addAdvertImg(imageBean);
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

    private void addAdvertImg(AdvertImageBean imageBean) {
        for (int i = 0; i < imageBean.list.size(); i++) {
            ImageView advertImg = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            advertImg.setScaleType(ImageView.ScaleType.FIT_XY);
            layoutParams.topMargin = 20;
            Glide.with(getActivity()).load("https://cdn.zomake.com/img/201701170935123448.jpg-advert").into(advertImg);
            headerView.addView(advertImg, layoutParams);
        }
    }

}
