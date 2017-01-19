package com.zomake.mobile.ui.Presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.zomake.mobile.api.ApiService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.contract.BaseContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Ryan on 17/1/18.
 */

public class MainShopPresenter extends BaseContract.MainPresenter {
    @Override
    public void getMainChannels() {
        Observable ob = HttpManager.getInstance().getHttpService(ApiService.class).getCatalogList();
        HttpManager.getInstance().toSubscribe(ob, new RxSubscriber<MainCatalogBean>(mContext){

            @Override
            protected void _onNext(MainCatalogBean mainCatalogBeen) {
                mView.showMainChannels(mainCatalogBeen);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }
}
