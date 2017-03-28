package com.zomake.mobile.ui.Presenter;

import android.text.TextUtils;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.CollectionUtils;
import com.jaydenxiao.common.commonutils.StringUtils;
import com.zomake.mobile.api.ApiService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.GoodAttrBean;
import com.zomake.mobile.bean.ProductDetailBean;
import com.zomake.mobile.bean.ProductDetailBean.DataEntity.AttachmentEntityXXXXXX.PropertyArrEntity;
import com.zomake.mobile.bean.SizecEnum;
import com.zomake.mobile.contract.BaseContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by wojiushiwn on 2017/3/15.
 * desc:
 */

public class ProductDetailPresenter extends BaseContract.AProductDetailPresenter {

    private Map<String, String> mAttrsMap;
    private Map<String, String> mCurAttrValuesMap;
    private List<String> mGoodAttrList;
    private Map<String, List<String>> mTempGoodListMap;
    private Map<String, List<GoodAttrBean.AttrDetail>> mGoodListMap;
    private List<String> mTempGoodsForSize;
    private List<String> mTempGoodsForSizec;
    private List<String> mTempGoodsForModel;
    private List<String> mTempGoodsForStyle;
    private List<GoodAttrBean.AttrDetail> mGoodsForSize;
    private List<GoodAttrBean.AttrDetail> mGoodsForSizec;
    private List<GoodAttrBean.AttrDetail> mGoodsForModel;
    private List<GoodAttrBean.AttrDetail> mGoodsForStyle;
    private List<PropertyArrEntity> mPropertyList;
    private double mRoyalty;
    private PropertyArrEntity mSelectGoods;
    private List<String> mPropertyKeyList;

    public ProductDetailPresenter() {
        init();
    }

    @Override
    public void getProductDetail(String eid) {
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class).getProductDetail(eid);
        mRxManager.add(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<ProductDetailBean>(mContext, false) {

            @Override
            protected void _onNext(ProductDetailBean productDetailBean) {
                mRoyalty = productDetailBean.getData().getRoyalty();
                mView.showProductDetail(productDetailBean);
                mergeProperty(productDetailBean);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    private void init() {
        if (mAttrsMap == null) {
            mAttrsMap = new HashMap<>();
            mAttrsMap.put("size", "可选尺寸");
            mAttrsMap.put("style", "可选款式");
            mAttrsMap.put("sizec", "可选尺码");
            mAttrsMap.put("model", "可选型号");
        }
        if (mCurAttrValuesMap == null) {
            mCurAttrValuesMap = new HashMap<>();
        }
        if (mTempGoodListMap == null) {
            mTempGoodListMap = new HashMap<>();
        }
        if (mGoodListMap == null) {
            mGoodListMap = new HashMap<>();
        }
    }

    @Override
    public void selectGood(String attr, int tagPosition) {
        String temp = mTempGoodListMap.get(attr).get(tagPosition);
        mCurAttrValuesMap.put(attr, temp);

        if (mCurAttrValuesMap.size() <= 1)
            return;

        mView.resetBuyButtonStatus(false);

        mRxManager.add(Observable.from(mPropertyList)
                .filter(propertyArrEntity ->
                        !mPropertyKeyList.contains("style") ||
                                propertyArrEntity.getStyle().equals(mCurAttrValuesMap.get("style")))
                .filter(propertyArrEntity ->
                        !mPropertyKeyList.contains("size") ||
                                propertyArrEntity.getSize().equals(mCurAttrValuesMap.get("size")))
                .filter(propertyArrEntity ->
                        !mPropertyKeyList.contains("sizec") ||
                                propertyArrEntity.getSizec().equals(mCurAttrValuesMap.get("sizec")))
                .filter(propertyArrEntity ->
                        !mPropertyKeyList.contains("model") ||
                                propertyArrEntity.getModel().equals(mCurAttrValuesMap.get("model")))
                .subscribe(propertyArrEntity -> {
                    int basePrice = propertyArrEntity.getRmb_price();
                    String subPrice = String.valueOf((int) (mRoyalty + basePrice));
                    mView.refreshBottomDialogPrice(subPrice);

                    mSelectGoods = propertyArrEntity;
                    mView.resetBuyButtonStatus(true);
                }));
    }

    private void mergeProperty(ProductDetailBean productDetailBean) {
        mView.addTypeViewList("商品类型", "订制商品", 0);

        if (!CollectionUtils.isNullOrEmpty(productDetailBean.getData().getAttachment().getPropertyArr())) {
            mPropertyList = productDetailBean.getData().getAttachment().getPropertyArr();
            mPropertyKeyList = productDetailBean.getData().getAttachment().getPropertyKeyArr();
            mGoodsForSize = new ArrayList<>();
            mGoodsForSizec = new ArrayList<>();
            mGoodsForModel = new ArrayList<>();
            mGoodsForStyle = new ArrayList<>();

            mTempGoodsForSize = new ArrayList<>();
            mTempGoodsForSizec = new ArrayList<>();
            mTempGoodsForModel = new ArrayList<>();
            mTempGoodsForStyle = new ArrayList<>();

            mRxManager.add(Observable.from(mPropertyList)
                    .subscribe(propertyArrEntity -> {
                        if (!TextUtils.isEmpty(propertyArrEntity.getSize()) &&
                                !mTempGoodsForSize.contains(propertyArrEntity.getSize())) {
                            mTempGoodsForSize.add(propertyArrEntity.getSize());
                            mGoodsForSize.add(new GoodAttrBean.AttrDetail(propertyArrEntity.getSize()));
                        }

                        if (!TextUtils.isEmpty(propertyArrEntity.getModel()) &&
                                !mTempGoodsForModel.contains(propertyArrEntity.getModel())) {
                            mTempGoodsForModel.add(propertyArrEntity.getModel());
                            mGoodsForModel.add(new GoodAttrBean.AttrDetail(propertyArrEntity.getModel()));
                        }

                        if (!TextUtils.isEmpty(propertyArrEntity.getSizec()) &&
                                !mTempGoodsForSizec.contains(propertyArrEntity.getSizec())) {
                            mTempGoodsForSizec.add(propertyArrEntity.getSizec());
                            mGoodsForSizec.add(new GoodAttrBean.AttrDetail(propertyArrEntity.getSizec()));
                        }

                        if (!TextUtils.isEmpty(propertyArrEntity.getStyle()) &&
                                !mTempGoodsForStyle.contains(propertyArrEntity.getStyle())) {
                            mTempGoodsForStyle.add(propertyArrEntity.getStyle());
                            mGoodsForStyle.add(new GoodAttrBean.AttrDetail(propertyArrEntity.getStyle()));
                        }
                    }));
            mTempGoodListMap.put("size", mTempGoodsForSize);
            mGoodListMap.put("size", mGoodsForSize);

            mTempGoodListMap.put("model", mTempGoodsForModel);
            mGoodListMap.put("model", mGoodsForModel);

            mTempGoodListMap.put("sizec", mTempGoodsForSizec);
            mGoodListMap.put("sizec", mGoodsForSizec);
            sortSizecList();

            mTempGoodListMap.put("style", mTempGoodsForStyle);
            mGoodListMap.put("style", mGoodsForStyle);

            if (!CollectionUtils.isNullOrEmpty(productDetailBean.getData().getAttachment().getPropertyKeyArr())) {
                List<String> keys = productDetailBean.getData().getAttachment().getPropertyKeyArr();
                for (int i = 0; i < keys.size(); i++) {
                    int position = 1;
                    if (i == (keys.size() - 1)) {
                        position = 2;
                    }
                    addTypeViewList(keys.get(i), position);
                }
            } else {
                mView.addTypeViewList("可选属性", "通用款式", 2);
            }
        }

        GoodAttrBean bean = new GoodAttrBean(1);
        mView.addBottomGoodList(bean);
        mView.addTypeViewList("", "", -1);
    }

    private void addTypeViewList(String attr, int position) {
        String attrValue = mAttrsMap.get(attr);
        List<String> list = mTempGoodListMap.get(attr);
        List<GoodAttrBean.AttrDetail> attrDetailList = mGoodListMap.get(attr);

        if (!CollectionUtils.isNullOrEmpty(list)) {
            mView.addTypeViewList(attrValue, StringUtils.join(list.toArray(), "  "), position);
        }
        if (!CollectionUtils.isNullOrEmpty(attrDetailList)) {
            GoodAttrBean bean = new GoodAttrBean(0, attr, attrValue, attrDetailList);
            mView.addBottomGoodList(bean);
        }
    }

    private void sortSizecList() {
        Collections.sort(mTempGoodsForSizec, (lhs, rhs) -> {
            String l = String.valueOf(SizecEnum.getIndex(lhs));
            String h = String.valueOf(SizecEnum.getIndex(rhs));
            return l.compareTo(h);
        });
        Collections.sort(mGoodsForSizec, (lhs, rhs) -> {
            String l = String.valueOf(SizecEnum.getIndex(lhs.getValue()));
            String h = String.valueOf(SizecEnum.getIndex(rhs.getValue()));
            return l.compareTo(h);
        });
    }

}
