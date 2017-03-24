package com.zomake.mobile.contract;

import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.zomake.mobile.bean.AdvertImageBean;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.bean.CatalogProductListBean.DataEntity.ProductArrEntity;
import com.zomake.mobile.bean.ProductDetailBean;
import com.zomake.mobile.bean.GoodAttrBean;

import java.util.List;

/**
 * 作者：Ryan on 16/12/6 13:53
 * 邮箱：zhenweiduan2@creditease.cn
 */
public interface BaseContract {
    //主页接口
    abstract class MainPresenter extends BasePresenter<MainView> {
        public abstract void getMainChannels();
    }

    interface MainView extends BaseView {
        void showMainChannels(MainCatalogBean mainCatalogBeen);
    }

    abstract class CatalogPresenter extends BasePresenter<CatalogView> {
        public abstract void getCatalogList(String parentId);

        public abstract void getAdvertList(String id);
    }

    interface CatalogView extends BaseView {
        void showCatalogList(CatalogProductsBean productsBean);

        void showAdvertList(AdvertImageBean imageBean);
    }

    abstract class AProductDetailPresenter extends BasePresenter<IProductDetailView> {

        public abstract void getProductDetail(String eid);

        public abstract void selectGood(String attr, int tagPosition);
    }

    interface IProductDetailView extends BaseView {

        void showProductDetail(ProductDetailBean productsBean);

        void addTypeViewList(String type, String typeDetail, int position);

        void addBottomGoodList(GoodAttrBean goodAttrBean);

        void refreshBottomDialogPrice(String price);

        void resetBuyButtonStatus(boolean isEnable);

    }

    abstract class ACatalogListPresenter extends BasePresenter<IModelListView> {

        public abstract void getCatalogList(String catalogId);
    }

    interface IModelListView extends BaseView {

        void showProductList(List<ProductArrEntity> productArrEntities);

    }

}
