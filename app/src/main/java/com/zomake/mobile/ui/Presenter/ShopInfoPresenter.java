package com.zomake.mobile.ui.Presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.zomake.mobile.api.ApiService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.CatalogProductListBean;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.bean.ShopDetailBean;
import com.zomake.mobile.contract.BaseContract;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by wojiushiwn on 2017/3/25.
 * desc:
 */

public class ShopInfoPresenter extends BaseContract.AShopInfoPresenter {
    private int page = 1;

    @Override
    public void getProductList(String shopId) {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("shopId", shopId);
        parameter.put("count", "16");
        parameter.put("page", String.valueOf(page));
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class)
                .getCatalogProductList(parameter);
        mRxManager.add(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<CatalogProductListBean>(mContext, false) {

            @Override
            protected void _onNext(CatalogProductListBean catalogProductListBean) {
                mView.showProductList(catalogProductListBean, false);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void getProductListForCatalog(String catalogId, boolean isRefresh) {
        String ids = "[\"" + catalogId + "\"]";
        Map<String, String> parameter = new HashMap<>();
        parameter.put("catalogIdArray", ids);
        parameter.put("count", "10");
        parameter.put("limit", String.valueOf(false));
        parameter.put("sort", "date");
        parameter.put("page", "1");
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class)
                .getCatalogProductList(parameter);
        mRxManager.add(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<CatalogProductListBean>(mContext, false) {

            @Override
            protected void _onNext(CatalogProductListBean catalogProductListBean) {
                mView.showProductList(catalogProductListBean, isRefresh);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void getShopDetail(String shopId) {
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class)
                .getShopDetail(shopId);
        mRxManager.add(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<ShopDetailBean>(mContext, false) {

            @Override
            protected void _onNext(ShopDetailBean bean) {
                mView.showShopDetail(bean);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void getCatalogList(String shopId) {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("shopId", shopId);
        parameter.put("count", "16");
        parameter.put("page", "1");
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class)
                .getCatalogList(parameter);
        mRxManager.add(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<MainCatalogBean>(mContext, false) {

            @Override
            protected void _onNext(MainCatalogBean bean) {
                mView.showCatalogList(bean);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
