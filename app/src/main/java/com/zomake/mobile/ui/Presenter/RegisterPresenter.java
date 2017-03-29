package com.zomake.mobile.ui.Presenter;

import android.widget.Toast;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.zomake.mobile.api.ApiAccountService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.contract.BaseContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class RegisterPresenter extends BaseContract.ARegisterPresenter {
    @Override
    public void sendSmsCode(String username) {


//        HttpManager.getInstance().getHttpService(ApiAccountService.class)
//                .repeat(username)

        mRxManager.add(HttpManager.getInstance().toSubscribe(
                HttpManager.getInstance().getHttpService(ApiAccountService.class)
                        .getSmsCode(username, true)
                        .filter(booleanBaseHttpResult -> booleanBaseHttpResult.result)
                , new RxSubscriber<Boolean>(mContext, true) {
                    @Override
                    protected void _onNext(Boolean booleanBaseHttpResult) {
                        Toast.makeText(mContext, "验证码发送成功", Toast.LENGTH_SHORT).show();
                        mView.showSendCodeView();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.stopLoading();
                        Toast.makeText(mContext, "验证码发送失败", Toast.LENGTH_SHORT).show();
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
                , new RxSubscriber(mContext, true) {
                    @Override
                    protected void _onNext(Object o) {

                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }
}
