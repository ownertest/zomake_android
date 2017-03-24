package com.zomake.mobile.ui.Presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.zomake.mobile.api.ApiService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.CatalogProductListBean;
import com.zomake.mobile.contract.BaseContract;

import rx.Observable;

/**
 * Created by wojiushiwn on 2017/3/23.
 * desc:
 */

public class CatalogListPresenter extends BaseContract.ACatalogListPresenter {

    @Override
    public void getCatalogList(String catalogId) {
        String ids = "[\"" + catalogId + "\"]";
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class)
                .getCatalogProductList(ids, 10, false, "data", 1);
        mRxManage.add(HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<CatalogProductListBean>(mContext, false) {

            @Override
            protected void _onNext(CatalogProductListBean catalogProductListBean) {
                mView.showProductList(catalogProductListBean.getData().getProductArr());
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
