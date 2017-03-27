package com.zomake.mobile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonwidget.BottomDialog;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.zomake.mobile.R;
import com.zomake.mobile.bean.CatalogProductListBean;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.bean.ShopDetailBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.ShopInfoPresenter;
import com.zomake.mobile.ui.fragment.ShopInfoFragment;
import com.zomake.mobile.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wojiushiwn on 2017/3/25.
 * desc:
 */

public class ShopInfoActivity extends BaseActivity<ShopInfoPresenter> implements BaseContract.IShopInfoView {

    public static final int MAX_TAB_SIZE = 2;
    @BindView(R.id.iv_header_bg)
    ImageView mIvHeaderBg;
    @BindView(R.id.tv_pi_name)
    TextView mTvPiName;
    @BindView(R.id.iv_city)
    ImageView mIvCity;
    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.rl_avatar)
    RelativeLayout mRlAvatar;
    @BindView(R.id.iv_pi_header)
    ImageView mIvPiHeader;
    @BindView(R.id.rl_header)
    RelativeLayout mRlHeader;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.iv_base_left)
    FontTextView mIvBaseLeft;
    @BindView(R.id.id_task_share_img)
    ImageView mIdTaskShareImg;
    @BindView(R.id.fl_toolbar)
    FrameLayout mFlToolbar;

    private String[] mChannelNameArr = {"商品", "心愿单"};

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    private BaseFragmentAdapter fragmentAdapter;
    private String mShopId;
    private CommonRecycleViewAdapter<MainCatalogBean.DataBean> mAdapter;
    private BottomDialog mBottomSheetDialog;
    private List<MainCatalogBean.DataBean> mCatalogList;

    @Override
    protected boolean isShowTitleBar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this);
    }

    @Override
    public void initView() {
        if (!TextUtils.isEmpty(mShopId)) {
            mPresenter.getShopDetail(mShopId);
        }

        initRefresh();
        List<String> channelNames = new ArrayList<>();
        List<Fragment> mNewsFragmentList = new ArrayList<>();
        for (int i = 0; i < MAX_TAB_SIZE; i++) {
            channelNames.add(mChannelNameArr[i]);
            mNewsFragmentList.add(createListFragments(mShopId));
        }
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), mNewsFragmentList, channelNames);
        }

        mViewPager.setAdapter(fragmentAdapter);
        mTabs.setupWithViewPager(mViewPager);
        MyUtils.dynamicSetTabLayoutMode(mTabs);
        mPresenter.getCatalogList(mShopId);

    }

    @Override
    public void initActionBar() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        int statusHeight = DisplayUtil.getStatusBarHeight(this);

        mToolbar.measure(0, 0);
        int measuredHeight = mToolbar.getMeasuredHeight();

        ViewGroup.LayoutParams layoutParams = mToolbar.getLayoutParams();
        layoutParams.height = measuredHeight + statusHeight;
        mToolbar.setLayoutParams(layoutParams);


        ViewGroup.LayoutParams params = mFlToolbar.getLayoutParams();
        params.height = measuredHeight + statusHeight;
        mFlToolbar.setLayoutParams(params);


    }

    private void initRefresh() {


        mIvHeaderBg.setScaleX(1.3f);
        mIvHeaderBg.setScaleY(1.3f);

        final int[] location = new int[2];
        mIvPiHeader.getLocationInWindow(location);
        final int endOffset = DisplayUtil.dip2px(this, 150);

        final int[] pointV = new int[2];
        mTvPiName.getLocationInWindow(pointV);
        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int[] ponitF = new int[2];
            int disten = -verticalOffset;
            int disOffset = endOffset;
            if (disten >= disOffset) {
                mIvPiHeader.setAlpha(0f);
                mRlAvatar.setAlpha(0);
                mIvHeaderBg.setScaleX(1.0f);
                mIvHeaderBg.setScaleY(1.0f);
            } else if (disten == 0) {
                mIvPiHeader.setAlpha(1f);
                mRlAvatar.setAlpha(1f);
                mIvHeaderBg.setScaleX(1.3f);
                mIvHeaderBg.setScaleY(1.3f);
            } else {
                float precents = (float) (disOffset - disten) / disOffset;
                mRlAvatar.setAlpha(precents);
                mIvPiHeader.setAlpha(precents);
                mIvHeaderBg.setScaleX(1.0f + 0.3f * (precents));
                mIvHeaderBg.setScaleY(1.0f + 0.3f * (precents));
            }


            mIvPiHeader.getLocationInWindow(ponitF);

            int y = ponitF[1];
            if (y > 0) {
                if (y < endOffset) {
                    float precent = (float) (endOffset - y) / endOffset;
                    mToolbar.setAlpha(precent);
                } else if (y >= endOffset) {
                    mToolbar.setAlpha(0);
                }
            } else if (y < 0) {
                mToolbar.setAlpha(1);
            } else if (y == 0) {
                mToolbar.setAlpha(1);
            }
        });
    }

    void initBottomDialog() {
        IRecyclerView bottomDialogRecyclerView = new IRecyclerView(this);
        bottomDialogRecyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.getScreenWidth(this)));
        bottomDialogRecyclerView.setRefreshEnabled(false);
        bottomDialogRecyclerView.setLoadMoreEnabled(false);
        bottomDialogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.line);
        textView.setTextColor(getResources().getColor(R.color.role_right_gray));
        textView.setTextSize(18);
        int paddingLeft = DisplayUtil.dip2px(this, 20);
        int paddingTop = DisplayUtil.dip2px(this, 10);
        textView.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
        textView.setBackgroundResource(R.drawable.line);
        bottomDialogRecyclerView.addHeaderView(textView);
        textView.setText("请选择分类");
        mAdapter =
                new CommonRecycleViewAdapter<MainCatalogBean.DataBean>(this, R.layout.catalog_itme_view) {
                    SparseArray<Set<Integer>> mSparseArray = new SparseArray<>();

                    @Override
                    public void convert(ViewHolderHelper helper, MainCatalogBean.DataBean bean) {
                        helper.setText(R.id.catalog_name, bean.name);
                    }
                };
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                String catalogId = mCatalogList.get(position - 2).id;
                if (!TextUtils.isEmpty(catalogId))
                    mPresenter.getProductListForCatalog(catalogId, true);

                if (mBottomSheetDialog != null) {
                    mBottomSheetDialog.dismiss();
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        bottomDialogRecyclerView.setAdapter(mAdapter);
        mAdapter.addAll(mCatalogList);
        mBottomSheetDialog = new BottomDialog(this);
        mBottomSheetDialog.setContentView(bottomDialogRecyclerView);

    }

    @Override
    public void initData(Bundle extras) {
        super.initData(extras);
        mShopId = (String) extras.get("shopId");
    }

    private ShopInfoFragment createListFragments(String shopId) {
        ShopInfoFragment fragment = new ShopInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("shopId", shopId);
        fragment.setArguments(bundle);
        return fragment;
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
        ImageLoaderUtils.display(this, mIvHeaderBg, ImageLoaderUtils.DEFAULT_URL_HOST + bean.getData().getBanner());
        ImageLoaderUtils.display(this, mIvPiHeader, ImageLoaderUtils.DEFAULT_URL_HOST + bean.getData().getLogo());
        mTvPiName.setText(bean.getData().getName());
        String info = TextUtils.isEmpty(bean.getData().getDesc_info()) ? "暂无简介" : bean.getData().getDesc_info();
        mTvInfo.setText(info);
    }

    @Override
    public void showProductList(CatalogProductListBean bean, boolean isRefresh) {
        if (fragmentAdapter.getItem(mViewPager.getCurrentItem()) != null &&
                fragmentAdapter.getItem(mViewPager.getCurrentItem()) instanceof ShopInfoFragment)
            ((ShopInfoFragment) fragmentAdapter.getItem(mViewPager.getCurrentItem())).showProductList(bean, isRefresh);
    }

    @Override
    public void showCatalogList(MainCatalogBean bean) {
        mCatalogList = bean.data;
        initBottomDialog();
    }

    @OnClick(R.id.btn_catalog)
    public void switchCatalog() {
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.showDialog();
        }
    }

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, ShopInfoActivity.class);
        intent.putExtra("shopId", shopId);
        context.startActivity(intent);
    }
}
