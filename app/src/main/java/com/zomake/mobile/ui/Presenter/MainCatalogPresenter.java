package com.zomake.mobile.ui.Presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.zomake.mobile.api.ApiService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.AdvertImageBean;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.fragment.CatalogFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
                addAdvertToGroupList(advertImageBean);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }

    private void addAdvertToGroupList(AdvertImageBean advertImageBean) {
        addRx(Observable.just(advertImageBean)
                .filter(advertImageBean1 -> advertImageBean1 != null && advertImageBean1.list != null && advertImageBean1.list.size() > 0)
                .flatMap(new Func1<AdvertImageBean, Observable<AdvertImageBean.ListBean>>() {
                    @Override
                    public Observable<AdvertImageBean.ListBean> call(AdvertImageBean advertImageBean) {
                        return Observable.from(advertImageBean.list);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    CatalogFragment.CatalogGroupItem groupItem = new CatalogFragment.CatalogGroupItem(bean,
                            CatalogFragment.TYPE_ITEM);
                    mView.showViewItem(groupItem);
                }));


    }

    public void addCatalogToGroupList(MainCatalogBean.DataBean.AttachmentBeanXX beanXX) {
        mView.showViewItem(new CatalogFragment.CatalogGroupItem(beanXX.children, CatalogFragment.TYPE_CATALOG));
    }

}
