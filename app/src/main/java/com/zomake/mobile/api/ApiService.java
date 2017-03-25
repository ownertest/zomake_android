package com.zomake.mobile.api;

import com.jaydenxiao.common.base.BaseHttpResult;
import com.zomake.mobile.bean.AdvertImageBean;
import com.zomake.mobile.bean.CatalogBean;
import com.zomake.mobile.bean.CatalogProductListBean;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.bean.MainCatalogBean;
import com.zomake.mobile.bean.ModelBean;
import com.zomake.mobile.bean.ProductDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ryan on 17/1/18.
 */

public interface ApiService {
    @GET("catalog/all")
    Observable<BaseHttpResult<MainCatalogBean>> getCatalogList();

    @GET("catalog/products")
    Observable<BaseHttpResult<CatalogProductsBean>> getProducts(@Query("parentId") String parentId);

    @GET("https://m.zomake.com/v1/advert/{id}")
    Observable<BaseHttpResult<AdvertImageBean>> getAdverts(@Path("id") String id);

    @GET("product/detail")
    Observable<BaseHttpResult<ProductDetailBean>> getProductDetail(@Query("eid") String eid);

    @GET("product/list")
    Observable<BaseHttpResult<CatalogProductListBean>> getCatalogProductList(@Query("catalogIdArray") String catalogIdArray,
                                                                             @Query("count") int count, @Query("limit") boolean limit,
                                                                             @Query("sort") String sort, @Query("page") int page);


    @GET("shop/detail")
    Observable<BaseHttpResult<ProductDetailBean>> getShopDetail(@Query("shopId") String shopId);

    @GET("catalog/products")
    Observable<BaseHttpResult<CatalogBean.DataEntity>> getCatalogList(@Query("parentId") String parentId);

    @GET("models")
    Observable<BaseHttpResult<ModelBean>> getModelList(@Query("catalogId") String catalogId);
}
