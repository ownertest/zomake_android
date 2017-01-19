package com.jaydenxiao.common.transformer;



import com.jaydenxiao.common.base.BaseHttpResult;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by duanzhenwei on 2016/11/6.
 * 23:28
 *
 */

public class CommonTransformer<T> implements Observable.Transformer<BaseHttpResult<T>, T> {

    @Override
    public Observable<T> call(Observable<BaseHttpResult<T>> tansFormerObservable) {
        return tansFormerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ErrorTransformer.<T>getInstance());
    }
}

