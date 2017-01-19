package com.jaydenxiao.common.transformer;


import com.jaydenxiao.common.base.BaseHttpResult;
import com.jaydenxiao.common.exception.ErrorType;
import com.jaydenxiao.common.exception.ExceptionEngine;
import com.jaydenxiao.common.exception.ServerException;

import rx.Observable;

/**
 * Created by duanzhenwei on 2016/11/6.
 * 23:00
 *
 */

public class ErrorTransformer<T> implements Observable.Transformer<BaseHttpResult<T>, T> {

    private static ErrorTransformer errorTransformer = null;
    private static final String TAG = "ErrorTransformer";

    @Override
    public Observable<T> call(Observable<BaseHttpResult<T>> responseObservable) {

        return responseObservable.map(httpResult -> {
            if (httpResult == null)
                throw new ServerException(ErrorType.EMPTY_BEAN, "解析对象为空");
            return httpResult.result;
        }).onErrorResumeNext(throwable -> {
            //ExceptionEngine为处理异常的驱动器throwable
            throwable.printStackTrace();
            return Observable.error(ExceptionEngine.handleException(throwable));
        });

    }

    /**
     * @return 线程安全, 双层校验
     */
    public static <T> ErrorTransformer<T> getInstance() {

        if (errorTransformer == null) {
            synchronized (ErrorTransformer.class) {
                if (errorTransformer == null) {
                    errorTransformer = new ErrorTransformer<>();
                }
            }
        }
        return errorTransformer;

    }
}
