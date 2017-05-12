package com.zomake.mobile.ui.Presenter;

import android.util.SparseArray;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.CollectionUtils;
import com.zomake.mobile.api.ApiService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.CatalogFilterBean;
import com.zomake.mobile.bean.CatalogFilterBean.ValueBean;
import com.zomake.mobile.bean.CatalogProductListBean;
import com.zomake.mobile.bean.ModelBean;
import com.zomake.mobile.contract.BaseContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by wojiushiwn on 2017/3/23.
 * desc:
 */

public class CatalogListPresenter extends BaseContract.ACatalogListPresenter {

    private ArrayList<CatalogFilterBean> mFilterBeanList;
    private SparseArray<ValueBean> mSelect = new SparseArray<>();

    @Override
    public void getCatalogProductList(String catalogId) {
        String ids = "[\"" + catalogId + "\"]";
        Map<String, String> parameter = new HashMap<>();
        parameter.put("catalogIdArray", ids);
        parameter.put("count", "10");
        parameter.put("limit", String.valueOf(false));
        parameter.put("sort", "date");
        parameter.put("page", "1");
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class)
                .getCatalogProductList(parameter);
        addRx(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<CatalogProductListBean>(mContext, false) {

            @Override
            protected void _onNext(CatalogProductListBean catalogProductListBean) {
                mView.showProductList(catalogProductListBean.getData().getProductArr());
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void getCatalogList(String catalogId) {
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class)
                .getModelList(catalogId);
        mRxManager.add(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<ModelBean>(mContext, false) {

            @Override
            protected void _onNext(ModelBean bean) {
                createCatalogRightData(bean);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void selectTag(int index, int childIndex) {
        if (CollectionUtils.isNullOrEmpty(mFilterBeanList)
                || mFilterBeanList.get(index) == null)
            return;

        CatalogFilterBean bean = mFilterBeanList.get(index);
        mSelect.put(index, bean.getValues().get(childIndex));
    }

    @Override
    public void resetTag() {
        mSelect.clear();
    }

    private void createCatalogRightData(ModelBean bean) {
        if (bean == null)
            return;

        mFilterBeanList = new ArrayList<>();

        List<ValueBean> cityList = initCityList();
        CatalogFilterBean cityFilterBean = new CatalogFilterBean(0, "发布地区", cityList);
        mFilterBeanList.add(cityFilterBean);

        List<ValueBean> styleList = initStyleList();
        CatalogFilterBean styleFilterBean = new CatalogFilterBean(1, "全部风格", styleList);
        mFilterBeanList.add(styleFilterBean);

        List<ValueBean> tagList = new ArrayList<>();
        mRxManager.add(Observable.from(bean.getData())
                .filter(dataEntity -> dataEntity.getAttachment() != null)
                .filter(dataEntity -> dataEntity.getAttachment().getNameArray() != null)
                .map(dataEntity -> dataEntity.getAttachment().getNameArray())
                .subscribe(strings -> {
                    String value = !CollectionUtils.isNullOrEmpty(strings) ? strings.get(0) : "";
                    tagList.add(new ValueBean(value, value));
                }));
        if (CollectionUtils.isNullOrEmpty(tagList))
            tagList.add(new ValueBean("所有单品", ""));
        CatalogFilterBean tagFilterBean = new CatalogFilterBean(2, "全部单品", tagList);
        mFilterBeanList.add(tagFilterBean);

        List<ValueBean> limitList = new ArrayList<>();
        limitList.add(new ValueBean("限量", "1"));
        limitList.add(new ValueBean("不限量", "0"));
        CatalogFilterBean limitFilterBean = new CatalogFilterBean(3, "是否限量", limitList);
        mFilterBeanList.add(limitFilterBean);

        mView.showCatalogList(mFilterBeanList);
    }

    private List<ValueBean> initCityList() {
        List<ValueBean> cityList = new ArrayList<>();
        cityList.add(new ValueBean("中国", "CN"));
        cityList.add(new ValueBean("香港", "HK"));
        cityList.add(new ValueBean("澳门", "MO"));
        cityList.add(new ValueBean("台湾", "TW"));
        cityList.add(new ValueBean("日本", "JP"));
        cityList.add(new ValueBean("韩国", "KR"));
        cityList.add(new ValueBean("新加坡", "SG"));
        cityList.add(new ValueBean("马来西亚", "MY"));
        cityList.add(new ValueBean("美国", "US"));
        cityList.add(new ValueBean("其他", "OTHER"));
        return cityList;
    }

    private List<ValueBean> initStyleList() {
        List<ValueBean> list = new ArrayList<>();
        list.add(new ValueBean("节日", "jr"));
        list.add(new ValueBean("涂鸦", "ty"));
        list.add(new ValueBean("艺术", "ys"));
        return list;
    }
}
