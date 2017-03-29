package com.zomake.mobile.api;

import com.jaydenxiao.common.base.BaseHttpResult;
import com.zomake.mobile.bean.UserInfoBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("https://passport.zomake.com/v1/captcha/sms")
    Observable<BaseHttpResult<Boolean>> getSmsCode(
            @Query("username") String username, @Query("second") boolean second);

    //?appid=581807bc59b9e60c94dfeddb&username=18026932863&country=cn&countrycode=86&password=123456&smscode=1642
    @POST("https://passport.zomake.com/v1/account/auth")
    Observable<BaseHttpResult<Object>> register(@QueryMap Map<String, String> parameters);

    @GET("https://passport.zomake.com/v1/account/auth/repeat")
    Observable<BaseHttpResult<Boolean>> repeat(@Query("username") String userName);

}
