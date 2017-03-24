package com.zomake.mobile.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.zomake.mobile.R;
import com.zomake.mobile.bean.CatalogProductListBean.DataEntity.ProductArrEntity;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.CatalogListPresenter;
import com.zomake.mobile.utils.MyUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wojiushiwn on 2017/3/23.
 * desc:
 */

public class CatalogProductListActivity extends BaseActivity<CatalogListPresenter>
        implements
        BaseContract.IModelListView {

    private enum Sort {
        DEFAULT(R.drawable.price_sort_default), DESC(R.drawable.price_sort_desc), ASC(R.drawable.price_sort_asc);

        private int sortImg;

        Sort(int sortImg) {
            this.sortImg = sortImg;
        }

        public int getSortImg() {
            return sortImg;
        }
    }

    private static final int MAX_DIST = MyUtils.dip2px(30);

    @BindView(R.id.recyclerView)
    IRecyclerView mRecyclerView;
    @BindView(R.id.title_bar)
    NormalTitleBar mTitleBar;
    @BindView(R.id.tv_newest)
    CheckedTextView mTvNewest;
    @BindView(R.id.tv_hottest)
    CheckedTextView mTvHottest;
    @BindView(R.id.tv_price_sort)
    CheckedTextView mTvPriceSort;
    @BindView(R.id.switch_img)
    ImageView mSwitchImg;
    @BindView(R.id.btn_layout_switch)
    FrameLayout mBtnLayoutSwitch;
    @BindView(R.id.tab_button_layout)
    LinearLayout mStickyHeaderView;
    private String catalogId;
    private CommonRecycleViewAdapter<ProductArrEntity> mLinearAdapter;
    private CommonRecycleViewAdapter<ProductArrEntity> mGridAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private CheckedTextView mStickyTvPriceSort;

    boolean isProductViewAsList = false;
    private ImageView switchImg;
    private int totalChange;

    private Sort sortEnum = Sort.DESC;

    @OnClick(R.id.btn_layout_switch)
    public void switchLayout() {
        isProductViewAsList = !isProductViewAsList;
        int btnImg = isProductViewAsList ?
                R.drawable.product_list_list_btn :
                R.drawable.product_list_grid_btn;
        switchImg.setImageResource(btnImg);
        mSwitchImg.setImageResource(btnImg);
        int position = isProductViewAsList
                ? mLinearLayoutManager.findFirstVisibleItemPosition()
                : mGridLayoutManager.findFirstVisibleItemPosition();
        mRecyclerView.setLayoutManager(
                isProductViewAsList ? mLinearLayoutManager : mGridLayoutManager);
        CommonRecycleViewAdapter adapter = isProductViewAsList ? mLinearAdapter : mGridAdapter;
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mRecyclerView.getLayoutManager().scrollToPosition(position);
    }

    @OnClick(R.id.tv_price_sort)
    public void sortPriceClick() {
        if (sortEnum == Sort.DEFAULT)
            sortEnum = Sort.DESC;
        else if (sortEnum == Sort.DESC)
            sortEnum = Sort.ASC;
        else if (sortEnum == Sort.ASC)
            sortEnum = Sort.DESC;
        else
            sortEnum = Sort.DEFAULT;

        mStickyTvPriceSort.setCompoundDrawables(null, null, getResources().getDrawable(sortEnum.getSortImg()), null);
        mTvPriceSort.setCompoundDrawables(null, null, getResources().getDrawable(sortEnum.getSortImg()), null);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_catalog_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this);
    }

    @Override
    public void initView() {

        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(isProductViewAsList ? mLinearLayoutManager : mGridLayoutManager);
        View head = LayoutInflater.from(this).inflate(R.layout.catalog_head_view, mRecyclerView, false);
        View btnSwitch = head.findViewById(R.id.btn_layout_switch);
        mStickyTvPriceSort = (CheckedTextView) head.findViewById(R.id.tv_price_sort);
        mStickyTvPriceSort.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.price_sort_default), null);
        mTvPriceSort.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.price_sort_default), null);
        mStickyTvPriceSort.setOnClickListener(v -> sortPriceClick());

        switchImg = (ImageView) head.findViewById(R.id.switch_img);
        btnSwitch.setOnClickListener(v -> switchLayout());
        mRecyclerView.addHeaderView(head);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalChange += dy;
                int tranY = Math.max(0, MAX_DIST - totalChange);
                mStickyHeaderView.setTranslationY(tranY);
                mStickyHeaderView.setVisibility(totalChange >= (MAX_DIST - 5) ? View.VISIBLE : View.GONE);
            }
        });

        mLinearAdapter = new CommonRecycleViewAdapter<ProductArrEntity>(this, R.layout.model_item_linear) {
            @Override
            public void convert(ViewHolderHelper helper, ProductArrEntity dataEntity) {
                helper.setText(R.id.product_name, dataEntity.getName());
                helper.setText(R.id.product_design, dataEntity.getShop_id());
                int price = dataEntity.getAttachment().getBasePriceMap().getRMB()
                        + dataEntity.getAttachment().getRoyaltyMap().getRMB();
                helper.setText(R.id.product_price, String.format("RMB %d", price));
                ImageLoaderUtils.display(CatalogProductListActivity.this, helper.getView(R.id.image),
                        "https://shop-cdn.zomake.com/" + dataEntity.getCover());
            }
        };

        mGridAdapter = new CommonRecycleViewAdapter<ProductArrEntity>(this, R.layout.model_item_grid) {
            @Override
            public void convert(ViewHolderHelper helper, ProductArrEntity dataEntity) {
                helper.setText(R.id.product_name, dataEntity.getName());
                int price = dataEntity.getAttachment().getBasePriceMap().getRMB()
                        + dataEntity.getAttachment().getRoyaltyMap().getRMB();
                helper.setText(R.id.product_price, String.format("RMB %d", price));
                ImageLoaderUtils.display(CatalogProductListActivity.this, helper.getView(R.id.image),
                        "https://shop-cdn.zomake.com/" + dataEntity.getCover());
            }
        };

        mRecyclerView.setAdapter(isProductViewAsList ? mLinearAdapter : mGridAdapter);
        mPresenter.getCatalogList(catalogId);
    }

    @Override
    public void initData(Bundle bundle) {
        catalogId = (String) bundle.get("catalogId");
        String catalogName = (String) bundle.get("catalogName");
        String parentName = (String) bundle.get("parentName");
        getTitleBar().setTitleText(catalogName);
        getTitleBar().setSecTvLeft(parentName);
        getTitleBar().getTvSecLeft().setBackgroundResource(R.drawable.text_border_1);
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
    public void showProductList(List<ProductArrEntity> productArrEntities) {
        mLinearAdapter.addAll(productArrEntities);
        mGridAdapter.addAll(productArrEntities);
    }

}
