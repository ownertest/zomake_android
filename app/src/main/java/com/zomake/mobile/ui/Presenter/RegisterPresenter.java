package com.zomake.mobile.ui.Presenter;

import android.widget.Toast;

import com.jaydenxiao.common.base.BaseHttpResult;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.zomake.mobile.api.ApiAccountService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.contract.BaseContract;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class RegisterPresenter extends BaseContract.ARegisterPresenter {
    @Override
    public void sendSmsCode(String username) {


        mRxManager.add(HttpManager.getInstance().toSubscribe(
                HttpManager.getInstance().getHttpService(ApiAccountService.class)
                        .repeat(username, System.currentTimeMillis())
                        .flatMap(new Func1<BaseHttpResult<Boolean>, Observable<BaseHttpResult<Boolean>>>() {
                            @Override
                            public Observable<BaseHttpResult<Boolean>> call(BaseHttpResult<Boolean> booleanBaseHttpResult) {
                                return HttpManager.getInstance().getHttpService(ApiAccountService.class)
                                        .getSmsCode(username, true, System.currentTimeMillis());

                            }
                        }).filter(booleanBaseHttpResult -> booleanBaseHttpResult.result)
                , new RxSubscriber<Boolean>(mContext, true) {
                    @Override
                    protected void _onNext(Boolean booleanBaseHttpResult) {
                        String msg = booleanBaseHttpResult ? "验证码发送成功" : "验证码发送失败";
                        ToastUitl.showShort(msg);
                        if (booleanBaseHttpResult)
                            mView.showSendCodeView();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.stopLoading();
                        ToastUitl.showShort("手机号码已注册");
                    }
                }));
    }

    @Override
    public void register(String username, String password, String smsCode) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("password", password);
        parameters.put("smscode", smsCode);

        mRxManager.add(HttpManager.getInstance().toSubscribe(
                HttpManager.getInstance().getHttpService(ApiAccountService.class)
                        .register(parameters)
                        .filter(booleanBaseHttpResult -> booleanBaseHttpResult.result)
                , new RxSubscriber<Boolean>(mContext, true) {
                    @Override
                    protected void _onNext(Boolean o) {
                        if (o)
                            mView.showRegisterView(username, password);
                        else
                            ToastUitl.showShort("手机号码已注册");
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }
}
