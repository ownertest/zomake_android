package com.zomake.mobile.api;


import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jaydenxiao.common.baserx.RetrofitCache;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.transformer.CommonTransformer;
import com.zomake.mobile.app.BaseApplication;
import com.zomake.mobile.bean.UserInfoBean;
import com.zomake.mobile.utils.DeviceUtil;
import com.zomake.mobile.utils.UserInfoManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;

public class HttpManager {
    private OkHttpClient client;
    private Retrofit retrofit;
    private String baseUrl = "https://shop-api.zomake.com/";
    private String baseAccountUrl = "https://passport.zomake.com/";

    /**
     * 构造方法私有
     */
    private HttpManager() {

    }

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public <T> T getHttpService(Class<T> clazz) {
        return getRetrofit(baseUrl).create(clazz);

    }

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public <T> T getHttpService(Class<T> clazz, String url) {
        return getRetrofit(url).create(clazz);

    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    /**
     * 获取单例
     */
    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            String[] country = DeviceUtil.getCountryZipCode(BaseApplication.getAppContext());

            HttpUrl.Builder url = originalRequest.url().newBuilder()
                    .scheme(originalRequest.url().scheme())
                    .host(originalRequest.url().host())
                    .addQueryParameter("appid", "582c16b6351a9f03fba3d7ea")
                    .addQueryParameter("country", country[1])
                    .addQueryParameter("countrycode", country[0]);
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .url(url.build())
                    // Provide your custom header here
//                        .header("token", (String) SpUtils.get("token", ""))
                    .method(originalRequest.method(), originalRequest.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private Retrofit getRetrofit(String baseUrl) {
        if (retrofit == null) {
            synchronized (HttpManager.class) {
                if (retrofit == null) {
                    //添加一个log拦截器,打印所有的log
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    //设置 请求的缓存的大小跟位置
                    File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
                    ClearableCookieJar cookieJar =
                            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getAppContext()));


                    client = new OkHttpClient
                            .Builder()
                            .addInterceptor(addHeaderInterceptor()) // token过滤
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
                            .cookieJar(cookieJar)
                            .cache(cache)  //添加缓存
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .writeTimeout(60L, TimeUnit.SECONDS)
                            .build();
                    // 获取retrofit的实例
                    retrofit = new Retrofit
                            .Builder()
                            .baseUrl(baseUrl)  //自己配置
                            .client(client)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create()) //这里是用的fastjson的
                            .build();
                }
            }
        }
        return retrofit;
    }

    public Subscription toSubscribe(Observable ob, final RxSubscriber subscriber) {
        //数据预处理
        //数据预处理
        Observable observable = ob.compose(new CommonTransformer())
                .doOnSubscribe(() -> {

                });
        return RetrofitCache.load("cache_default", observable, false, true).subscribe(subscriber);
    }

}