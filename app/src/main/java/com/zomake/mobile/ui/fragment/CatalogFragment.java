package com.zomake.mobile.ui.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonwidget.NoScrollGridView;
import com.jaydenxiao.common.commonwidget.OnDoubleClickListener;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview
        .MultiItemRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.zomake.mobile.R;
import com.zomake.mobile.app.AppConstant;
import com.zomake.mobile.bean.AdvertImageBean;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.bean.MainCatalogBean.DataBean.AttachmentBeanXX.ChildrenBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.MainCatalogPresenter;
import com.zomake.mobile.ui.activity.CatalogProductListActivity;
import com.zomake.mobile.ui.activity.ProductDetailActivity;


import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


public class CatalogFragment extends BaseFragment<MainCatalogPresenter> implements BaseContract
        .CatalogView {

    public static final int TYPE_ITEM = 0x11;
    public static final int TYPE_CATALOG = 0x12;


    @BindView(R.id.tv_recyclerView)
    IRecyclerView recyclerView;
    private MultiItemRecycleViewAdapter<CatalogGroupItem> adapter;
    private MainCatalogBean.DataBean.AttachmentBeanXX childrenBeanList;
    //    private LinearLayout headerView;
    private String mParentId;

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
            childrenBeanList = (MainCatalogBean.DataBean.AttachmentBeanXX) getArguments()
                    .getSerializable(AppConstant.CATALOG_MENU);
            mParentId = getArguments().getString(AppConstant.CATALOG_ID);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MultiItemRecycleViewAdapter<CatalogGroupItem>(getActivity(), new
                MultiItemTypeSupport<CatalogGroupItem>() {
                    @Override
                    public int getLayoutId(int itemType) {
                        return itemType == TYPE_ITEM ? R.layout.advert_item_layout : R.layout.catalog_grid_layout;
                    }

                    @Override
                    public int getItemViewType(int position, CatalogGroupItem item) {
                        return item.type;
                    }
                }) {
            @Override
            public void convert(ViewHolderHelper helper, CatalogGroupItem item) {
                switch (item.type) {
                    case TYPE_CATALOG:
                        List<ChildrenBean> list = (List<ChildrenBean>) item.o;
                        CatalogGridAdapter gridAdapter = new CatalogGridAdapter(getActivity(), R.layout
                                .catalog_item_layout, list);
                        NoScrollGridView r = helper.getView(R.id.grid_catalog);
                        r.setNumColumns(5);
                        r.setAdapter(gridAdapter);
                        break;
                    case TYPE_ITEM:
                        AdvertImageBean.ListBean bean = (AdvertImageBean.ListBean) item.o;
                        ImageView advertImg = helper.getView(R.id.image);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                                DisplayUtil.getScreenWidth(getActivity()),
                                DisplayUtil.getScreenWidth(getActivity()));
                        layoutParams.topMargin = DisplayUtil.dip2px(getActivity(), 10);
                        advertImg.setLayoutParams(layoutParams);
                        advertImg.setOnClickListener(v -> ProductDetailActivity.startActivity(getActivity(), bean.eid));
                        ImageLoaderUtils.display(getActivity(), advertImg, "https://cdn.zomake.com/" + bean
                                .image);
                        break;
                    default:
                        break;
                }
            }
        };

        //                R.layout.catalog_item_layout) {
        //            @Override
        //            public void convert(ViewHolderHelper helper, MainCatalogBean.DataBean
        // .AttachmentBeanXX.ChildrenBean productsDataBean) {
        //                helper.setText(R.id.name, productsDataBean.name);
        //                Glide.with(getActivity()).load("https://shop-cdn.zomake.com/" +
        // productsDataBean.banner).into((ImageView) helper.getView(R.id.image));
        //            }
        //        };
        //        headerView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout
        // .advert_item_layout, recyclerView, false);
        recyclerView.setAdapter(adapter);
        //        recyclerView.addFooterView(headerView);
        //        adapter.addAll(childrenBeanList.getChildren());
        mPresenter.addCatalogToGroupList(childrenBeanList);
        mPresenter.getCatalogList(mParentId);
        mPresenter.getAdvertList(mParentId);
    }

    @Override
    public void showViewList(List<CatalogGroupItem> list) {
        adapter.addAll(list);
    }

    @Override
    public void showViewItem(CatalogGroupItem item) {
        adapter.add(item);
    }

    @Override
    public void showCatalogList(CatalogProductsBean productsBean) {
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

    //    private void addAdvertImg(AdvertImageBean imageBean) {
    //        for (int i = 0; i < imageBean.list.size(); i++) {
    //            ImageView advertImg = new ImageView(getActivity());
    //            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
    // .LayoutParams.MATCH_PARENT,
    //                    DisplayUtil.getScreenWidth(getActivity()));
    //            advertImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
    //            layoutParams.topMargin = 20;
    //            ImageLoaderUtils.display(getActivity(), advertImg, "https://cdn.zomake.com/" +
    // imageBean.list.get(i).image);
    //            headerView.addView(advertImg, layoutParams);
    //        }
    //    }

    public static class CatalogGroupItem {

        private Object o;
        private int type;

        public CatalogGroupItem(Object o, int type) {
            this.o = o;
            this.type = type;
        }
    }

    private class CatalogGridAdapter extends CommonAblistViewAdapter<ChildrenBean> {
        CatalogGridAdapter(Context context, int layoutId, List<ChildrenBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolderHelper holder, ChildrenBean childrenBean) {
            holder.setText(R.id.name, childrenBean.name);
            holder.setOnClickListener(R.id.catalog_root, v -> {
                CatalogProductListActivity.startActivity(getActivity(), childrenBean.id, childrenBean
                        .name, childrenBean.parent.name);
            });
            ImageLoaderUtils.display(getActivity(), holder.getView(R.id.image), "https://shop-cdn" +
                    ".zomake.com/" + childrenBean.banner);
        }

        @Override
        public void set(List<ChildrenBean> elements) {
            mDatas = elements;
        }

        @Override
        public void resetAt(int location, List<ChildrenBean> elements) {
        }

        @Override
        public void resetRange(int start, int count, List<ChildrenBean> elements) {
        }
    }

}
