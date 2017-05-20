package com.zomake.mobile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.CollectionUtils;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zomake.mobile.R;
import com.zomake.mobile.bean.CatalogFilterBean;
import com.zomake.mobile.bean.CatalogProductListBean;
import com.zomake.mobile.bean.CatalogProductListBean.DataEntity.ProductArrEntity;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.CatalogListPresenter;
import com.zomake.mobile.utils.MyUtils;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wojiushiwn on 2017/3/23.
 * desc:
 */

public class CatalogProductListActivity extends BaseActivity<CatalogListPresenter>
        implements
        BaseContract.IModelListView {

    private static final int MAX_DIST = MyUtils.dip2px(30);

    @BindView(R.id.drawer_root)
    DrawerLayout mDrawerRoot;
    @BindView(R.id.recyclerView)
    IRecyclerView mRecyclerView;
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
    @BindView(R.id.bottom_buttons)
    LinearLayout mBottomButtons;

    @BindView(R.id.city_root)
    View mCityRoot;
    @BindView(R.id.good_root)
    View mGoodRoot;
    @BindView(R.id.style_root)
    View mStyleRoot;
    @BindView(R.id.limit_root)
    View mLimitRoot;

    private String catalogId;
    private CommonRecycleViewAdapter<ProductArrEntity> mLinearAdapter;
    private CommonRecycleViewAdapter<ProductArrEntity> mGridAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private CheckedTextView mStickyTvPriceSort;
    private ImageView switchImg;

    TextView mCityTypeName;
    TagFlowLayout mCityTagFlowLayout;
    TextView mGoodTypeName;
    TagFlowLayout mGoodTagFlowLayout;
    TextView mStyleTypeName;
    TagFlowLayout mStyleTagFlowLayout;
    TextView mLimitTypeName;
    TagFlowLayout mLimitTagFlowLayout;

    boolean isProductViewAsList = false;
    private int totalChange;

    private Sort sortEnum = Sort.DESC;

    @OnClick(R.id.btn_commit)
    public void commitSortClick() {
        mDrawerRoot.closeDrawers();
    }

    @OnClick(R.id.btn_reset)
    public void resetSortClick() {
        mCityTagFlowLayout.getAdapter().setSelectedList(new HashSet<>());
        mGoodTagFlowLayout.getAdapter().setSelectedList(new HashSet<>());
        mStyleTagFlowLayout.getAdapter().setSelectedList(new HashSet<>());
        mLimitTagFlowLayout.getAdapter().setSelectedList(new HashSet<>());
        mPresenter.resetTag();
    }

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
    }

    @Override
    public void initView() {

        initRightFilterDialog();
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

        mLinearAdapter.setOnItemClickListener(onItemClickListener);
        mGridAdapter.setOnItemClickListener(onItemClickListener);

        mRecyclerView.setAdapter(isProductViewAsList ? mLinearAdapter : mGridAdapter);
        mPresenter.getCatalogProductList(catalogId);
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
        getTitleBar().setRightImagSrc(R.drawable.filter_property);
        getTitleBar().setOnRightImagListener(v -> {
            if (!mDrawerRoot.isDrawerOpen(GravityCompat.END))
                mDrawerRoot.openDrawer(GravityCompat.END);
            else
                mDrawerRoot.closeDrawer(GravityCompat.END);
        });
    }

    OnItemClickListener<ProductArrEntity> onItemClickListener = new OnItemClickListener<ProductArrEntity>() {
        @Override
        public void onItemClick(ViewGroup parent, View view, ProductArrEntity productArrEntity, int position) {
            if (productArrEntity != null) {
                goProductDetail(productArrEntity.getId());
            }
        }

        @Override
        public boolean onItemLongClick(ViewGroup parent, View view, ProductArrEntity productArrEntity, int position) {
            return false;
        }
    };

    private void goProductDetail(String eid) {
        ProductDetailActivity.startActivity(this, eid);
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

    @Override
    public void showCatalogList(List<CatalogFilterBean> catalogBeanList) {
        mRxManager.add(Observable.from(catalogBeanList)
                .filter(catalogFilterBean -> !CollectionUtils.isNullOrEmpty(catalogBeanList))
                .subscribe(bean -> {
                    TextView textView = bean.getIndex() == 0 ?
                            mCityTypeName : bean.getIndex() == 1 ?
                            mGoodTypeName : bean.getIndex() == 2 ?
                            mStyleTypeName : bean.getIndex() == 3 ?
                            mLimitTypeName : null;
                    if (textView != null)
                        textView.setText(bean.getSectionTitle());

                    TagFlowLayout tagFlowLayout = bean.getIndex() == 0 ?
                            mCityTagFlowLayout : bean.getIndex() == 1 ?
                            mGoodTagFlowLayout : bean.getIndex() == 2 ?
                            mStyleTagFlowLayout : bean.getIndex() == 3 ?
                            mLimitTagFlowLayout : null;

                    if (tagFlowLayout != null) {
                        tagFlowLayout.setAdapter(getTagAdapter(bean.getValues()));
                        tagFlowLayout.setOnSelectListener(getSelectListener(bean.getIndex()));
                    }
                }));
    }

    private TagFlowLayout.OnSelectListener getSelectListener(int type) {
        return selectPosSet -> {
            if (selectPosSet.iterator().hasNext()) {
                mPresenter.selectTag(type, selectPosSet.iterator().next());
            }
        };
    }

    private TagAdapter getTagAdapter(List<CatalogFilterBean.ValueBean> list) {

        return new TagAdapter<CatalogFilterBean.ValueBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, CatalogFilterBean.ValueBean s) {
                TextView textView = (TextView) LayoutInflater.from(CatalogProductListActivity.this).inflate(R.layout.good_itme_view, parent, false);
                textView.setText(s.getValue());
                return textView;
            }
        };
    }

    private void initRightFilterDialog() {
        mCityTypeName = (TextView) mCityRoot.findViewById(R.id.good_type_name);
        mCityTagFlowLayout = (TagFlowLayout) mCityRoot.findViewById(R.id.good_list);
        mGoodTypeName = (TextView) mGoodRoot.findViewById(R.id.good_type_name);
        mGoodTagFlowLayout = (TagFlowLayout) mGoodRoot.findViewById(R.id.good_list);
        mStyleTypeName = (TextView) mStyleRoot.findViewById(R.id.good_type_name);
        mStyleTagFlowLayout = (TagFlowLayout) mStyleRoot.findViewById(R.id.good_list);
        mLimitTypeName = (TextView) mLimitRoot.findViewById(R.id.good_type_name);
        mLimitTagFlowLayout = (TagFlowLayout) mLimitRoot.findViewById(R.id.good_list);
    }

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

    public static void startActivity(Context context, String catalogId, String catalogName, String parentName) {
        Intent intent = new Intent(context, CatalogProductListActivity.class);
        intent.putExtra("catalogId", catalogId);
        intent.putExtra("catalogName", catalogName);
        intent.putExtra("parentName", parentName);
        context.startActivity(intent);
    }
}
