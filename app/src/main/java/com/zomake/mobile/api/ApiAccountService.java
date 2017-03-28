package com.zomake.mobile.api;

import com.jaydenxiao.common.base.BaseHttpResult;
import com.zomake.mobile.bean.CatalogProductsBean;
import com.zomake.mobile.bean.UserInfoBean;

import java.util.Map;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by wojiushiwn on 2017/3/27.
 * desc:
 */

public interface ApiAccountService {

    //登录
    @PUT("https://passport.zomake.com/v1/account/auth")
    Observable<BaseHttpResult<Boolean>> login(
            @QueryMap Map<String, String> parameter);

    @DELETE("https://passport.zomake.com/v1/account/auth")
    Observable<BaseHttpResult<Boolean>> logout();

    //获取user 相关信息
    @GET("https://passport.zomake.com/v1/account/auth")
    Observable<BaseHttpResult<UserInfoBean>> getUser();

    //获取user 详细信息
    @GET("https://passport.zomake.com/v1/data")
    Observable<BaseHttpResult<UserInfoBean>> getUserInfo();

}
