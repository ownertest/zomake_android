package com.zomake.mobile.ui.Presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.zomake.mobile.api.ApiService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.AdvertImageBean;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.contract.BaseContract;

import rx.Observable;

/**
 * Created by Ryan on 17/1/19.
 */

public class MainCatalogPresenter extends BaseContract.CatalogPresenter {
    @Override
    public void getCatalogList(String parentId) {
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class).getProducts(parentId);
        HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<CatalogProductsBean>(mContext, false) {

            @Override
            protected void _onNext(CatalogProductsBean mainCatalogBeen) {
                mView.showCatalogList(mainCatalogBeen);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }

    @Override
    public void getAdvertList(String id) {
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class).getAdverts(id);
        HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<AdvertImageBean>(mContext, false) {

            @Override
            protected void _onNext(AdvertImageBean advertImageBean) {
                mView.showAdvertList(advertImageBean);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }

}
