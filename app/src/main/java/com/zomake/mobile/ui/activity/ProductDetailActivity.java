package com.zomake.mobile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonwidget.BottomDialog;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zomake.mobile.R;
import com.zomake.mobile.bean.GoodAttrBean;
import com.zomake.mobile.bean.ProductDetailBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.ProductDetailPresenter;
import com.zomake.mobile.utils.MyUtils;
import com.zomake.mobile.widget.CounterView.CounterView;
import com.zomake.mobile.widget.CounterView.IChangeCountCallback;

import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements BaseContract.IProductDetailView {


    CommonRecycleViewAdapter<String> mAdapter;
    CommonRecycleViewAdapter<GoodAttrBean> mGoodListAdapter;

    ImageView mProductCover;
    FontTextView mProductName;
    FontTextView mProductPrice;
    FontTextView mProductOldPrice;
    TextView mShopName;
    TextView mShopInfo;
    View mBottomDialogView;
    BottomDialog mBottomSheetDialog;


    private LinearLayout mHeaderView;
    @BindView(R.id.recyclerView)
    IRecyclerView mRecyclerView;
    @BindView(R.id.detail_bottom)
    LinearLayout mDetailBottom;
    @BindView(R.id.btn_car)
    Button mBtnCar;
    private TextView mTvCommit;
    private TextView mTvSubPrice;
    private TextView mTvNot;

    private int mSubPrice;
    private int mBasePrice;
    private RelativeLayout mBtnCommit;
    private String mShopId;
    private String eid;


    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this);
    }

    @Override
    public void initData(Bundle extras) {
        super.initData(extras);

        if (extras != null) {
            eid = extras.getString("eid");
        }
    }

    @Override
    public void initView() {
        getTitleBar().setRightTitle("");
        getTitleBar().setVisibleBottomLine(false);
        getTitleBar().setOnRightImagListener(v -> showLongToast("分享啦！！！"));
        initBottomDialog();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommonRecycleViewAdapter<String>(this, R.layout.product_item_img_layout) {
            @Override
            public void convert(ViewHolderHelper helper, String url) {
                ImageLoaderUtils.displayIntoUseFitWidth(ProductDetailActivity.this, helper.getView(R.id.image), "https://shop-cdn.zomake.com/" + url);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mHeaderView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.product_head_view, mRecyclerView, false);
        int width = MyUtils.getScreenWith();
        mProductCover = (ImageView) mHeaderView.findViewById(R.id.product_cover);
        mProductName = (FontTextView) mHeaderView.findViewById(R.id.product_name);
        mProductOldPrice = (FontTextView) mHeaderView.findViewById(R.id.product_old_price);
        mProductPrice = (FontTextView) mHeaderView.findViewById(R.id.product_price);
        mRecyclerView.addHeaderView(mHeaderView);
        View mFooterShopInfoView = LayoutInflater.from(this).inflate(R.layout.product_footer_shop_info, null);
        mShopName = (TextView) mFooterShopInfoView.findViewById(R.id.shop_name);
        mShopInfo = (TextView) mFooterShopInfoView.findViewById(R.id.shop_info);
        mFooterShopInfoView.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mShopId)) {
                ShopInfoActivity.startActivity(mContext, mShopId);
            }
        });
        mRecyclerView.addFooterView(mFooterShopInfoView);
        View mFooterOtherInfoView = LayoutInflater.from(this).inflate(R.layout.product_footer_other_info, null);
        mRecyclerView.addFooterView(mFooterOtherInfoView);
        mPresenter.getProductDetail("57d5573f3916c325f8e9c1cc");
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
    public void showProductDetail(ProductDetailBean productDetailBean) {
        if (productDetailBean == null || productDetailBean.getData() == null || productDetailBean.getData().getAttachment() == null)
            return;

        mShopId = productDetailBean.getData().getShop_id();

        ImageLoaderUtils.displayIntoUseFitWidth(this, mProductCover, "https://shop-cdn.zomake.com/" + productDetailBean.getData().getAttachment().getImgArray().get(0));
        mProductName.setText(productDetailBean.getData().getName());
        double royalty = productDetailBean.getData().getRoyalty();
        int basePrice = productDetailBean.getData().getAttachment().getPropertyArr().get(0).getRmb_price();
        int subPrice = (int) (royalty + basePrice);
        mProductPrice.setText(String.valueOf(subPrice));
        mProductOldPrice.setText(String.valueOf(subPrice + 49));
        mProductOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        mShopName.setText(productDetailBean.getData().getAttachment().getShop().getName());
        mShopInfo.setText(productDetailBean.getData().getAttachment().getShop().getDesc_info());

        mTvSubPrice.setText(String.format("%s元", subPrice));

        mBasePrice = subPrice;
        mSubPrice = subPrice;

        if (productDetailBean.getData().getAttachment().getTemplate() != null && mAdapter != null) {
            mAdapter.addAll(productDetailBean.getData().getAttachment().getTemplate().getAttachment().getCnDesc());
        }
    }

    @Override
    public void addTypeViewList(String type, String typeDetail, int position) {

        if (position == -1) {
            addLineView();
            return;
        }

        View mTypeView = LayoutInflater.from(this).inflate(R.layout.product_item_info_layout, null);
        FontTextView mType = (FontTextView) mTypeView.findViewById(R.id.product_type);
        FontTextView mTypeDetail = (FontTextView) mTypeView.findViewById(R.id.product_type_detail);
        mType.setText(type);
        mTypeDetail.setText(typeDetail);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = position == 0 ? MyUtils.dip2px(20) : MyUtils.dip2px(8);
        layoutParams.bottomMargin = position == 2 ? MyUtils.dip2px(20) : MyUtils.dip2px(8);
        mHeaderView.addView(mTypeView, layoutParams);


    }

    @Override
    public void addBottomGoodList(GoodAttrBean goodAttrBean) {
        if (goodAttrBean == null) return;

        mGoodListAdapter.add(goodAttrBean);
    }

    @Override
    public void refreshBottomDialogPrice(String price) {
        mTvSubPrice.setText(String.format("%s元", price));
    }

    @Override
    public void resetBuyButtonStatus(boolean isEnable) {
        int commitVisible = isEnable ? View.VISIBLE : View.GONE;
        int notVisible = isEnable ? View.GONE : View.VISIBLE;
        int color = isEnable ? R.color.blue : R.color.night_two_text_color;

        mTvCommit.setVisibility(commitVisible);
        mTvSubPrice.setVisibility(commitVisible);
        mTvNot.setVisibility(notVisible);
        mBtnCommit.setBackgroundColor(getResources().getColor(color));
        mBtnCommit.setEnabled(isEnable);
    }

    private void addLineView() {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(getResources().getColor(R.color.gray));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyUtils.dip2px(5));
        mHeaderView.addView(imageView, layoutParams);
    }

    private void initBottomDialog() {
        mBottomDialogView = LayoutInflater.from(this).inflate(R.layout.product_bottom_view, null);
        mTvCommit = (TextView) mBottomDialogView.findViewById(R.id.tv_commit);
        mTvNot = (TextView) mBottomDialogView.findViewById(R.id.tv_not);
        mBtnCommit = (RelativeLayout) mBottomDialogView.findViewById(R.id.btn_commit);
        mTvSubPrice = (TextView) mBottomDialogView.findViewById(R.id.tv_sub_price);
        mTvCommit.setOnClickListener(v -> Toast.makeText(ProductDetailActivity.this, (int) v.getTag() == R.id.btn_buy ? "买买买" : "加加加", Toast.LENGTH_SHORT).show());
        IRecyclerView bottomDialogRecyclerView = (IRecyclerView) mBottomDialogView.findViewById(R.id.recyclerView);
        bottomDialogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGoodListAdapter =
                new MultiItemRecycleViewAdapter<GoodAttrBean>(this, new MultiItemTypeSupport<GoodAttrBean>() {
                    @Override
                    public int getLayoutId(int itemType) {
                        if (itemType == 0) {
                            return R.layout.product_detail_dialog_item;
                        } else
                            return R.layout.good_car_count_view;
                    }

                    @Override
                    public int getItemViewType(int position, GoodAttrBean goodAttrBean) {
                        return goodAttrBean.getItemType();
                    }
                }) {
                    SparseArray<Set<Integer>> mSparseArray = new SparseArray<>();

                    @Override
                    public void convert(ViewHolderHelper helper, GoodAttrBean goodAttrBean) {
                        if (goodAttrBean.getItemType() == 0) {
                            helper.setText(R.id.good_type_name, goodAttrBean.getAttrValue());
                            TagFlowLayout tagFlowLayout = helper.getView(R.id.good_list);
                            tagFlowLayout.setTag(goodAttrBean.getAttr());
                            TagAdapter<GoodAttrBean.AttrDetail> tagAdapter = new TagAdapter<GoodAttrBean.AttrDetail>(goodAttrBean.getTypeDetailList()) {
                                @Override
                                public View getView(FlowLayout parent, int position, GoodAttrBean.AttrDetail s) {
                                    TextView textView = (TextView) mInflater.inflate(R.layout.good_itme_view, parent, false);
                                    textView.setText(s.getValue());
                                    return textView;
                                }
                            };
                            tagFlowLayout.setAdapter(tagAdapter);
                            tagAdapter.setSelectedList(mSparseArray.get(helper.getAdapterPosition() - 2));
                            tagFlowLayout.setOnSelectListener(selectPosSet -> {
                                mSparseArray.put(helper.getAdapterPosition() - 2, selectPosSet);
                                if (selectPosSet.iterator().hasNext())
                                    mPresenter.selectGood((String) tagFlowLayout.getTag(), selectPosSet.iterator().next());

                            });
                        } else {
                            CounterView counterView = helper.getView(R.id.counter_view);
                            counterView.setMaxValue(99);
                            counterView.setCallback(callback);
                        }

                    }
                };
        bottomDialogRecyclerView.setAdapter(mGoodListAdapter);
        mBottomSheetDialog = new BottomDialog(this);
        mBottomSheetDialog.setContentView(mBottomDialogView);
    }

    @OnClick({R.id.btn_car, R.id.btn_buy})
    public void btnCarClick(View view) {
        if (mBottomSheetDialog != null) {
            mTvCommit.setText(view.getId() == R.id.btn_buy ? "立即购买" : "加入购物车");
            mTvCommit.setTag(view.getId());
            mBottomSheetDialog.showDialog();
        }
    }


    private IChangeCountCallback callback = count -> {            //总价变化
        mSubPrice = mBasePrice * count;
        mTvSubPrice.setText(String.format("%d元", mSubPrice));
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
            mBottomSheetDialog = null;
        }
    }

    public static void startActivity(Context context, String eid) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("eid", eid);
        context.startActivity(intent);
    }
}
