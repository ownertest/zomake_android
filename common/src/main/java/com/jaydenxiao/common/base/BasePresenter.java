package com.jaydenxiao.common.base;

import android.content.Context;

import com.jaydenxiao.common.baserx.RxManager;

/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BasePresenter<T>{
    public Context mContext;
    public T mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(T v) {
        this.mView = v;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManager.clear();
    }
}
