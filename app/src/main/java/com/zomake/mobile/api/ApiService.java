package com.zomake.mobile.api;

import com.jaydenxiao.common.base.BaseHttpResult;
import com.zomake.mobile.bean.AdvertImageBean;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.bean.MainCatalogBean;

import java.util.List;

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
}
