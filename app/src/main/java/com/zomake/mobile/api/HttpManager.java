package com.zomake.mobile.api;


import com.jaydenxiao.common.baserx.RetrofitCache;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.transformer.CommonTransformer;
import com.zomake.mobile.app.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class HttpManager {
    private OkHttpClient client;
    private Retrofit retrofit;
    private String baseUrl = "https://shop-api.zomake.com/";

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
        Interceptor headerInterceptor = chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
//                        .header("token", (String) SpUtils.get("token", ""))
                    .method(originalRequest.method(), originalRequest.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
        return headerInterceptor;
    }

    public Retrofit getRetrofit(String baseUrl) {
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

                    client = new OkHttpClient
                            .Builder()
                            .addInterceptor(addHeaderInterceptor()) // token过滤
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
//                            .cookieJar(new CookiesManager())
                            .cache(cache)  //添加缓存
                            .connectTimeout(60l, TimeUnit.SECONDS)
                            .readTimeout(60l, TimeUnit.SECONDS)
                            .writeTimeout(60l, TimeUnit.SECONDS)
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

    public void toSubscribe(Observable ob, final RxSubscriber subscriber) {
        //数据预处理
        //数据预处理
        Observable observable = ob.compose(new CommonTransformer())
                .doOnSubscribe(() -> {

                });
        RetrofitCache.load("cache_default", observable, false, true).subscribe(subscriber);
    }

}