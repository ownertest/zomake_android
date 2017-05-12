package com.zomake.mobile.ui.Presenter;

import com.jaydenxiao.common.base.BaseHttpResult;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.zomake.mobile.api.ApiAccountService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.UserInfoBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.event.UserChangeEvent;
import com.zomake.mobile.utils.UserInfoManager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by wojiushiwn on 2017/3/28.
 * desc:
 */

public class LoginPresenter extends BaseContract.ALoginPresenter {
    @Override
    public void login(String userName, String password) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", userName);
        parameters.put("password", password);

        Observable<BaseHttpResult<Boolean>> loginOb = HttpManager.getInstance().getHttpService(ApiAccountService.class)
                .login(parameters);
        Observable getUserInfo = HttpManager.getInstance().getHttpService(ApiAccountService.class)
                .getUserInfo();
        Observable getUser = HttpManager.getInstance().getHttpService(ApiAccountService.class)
                .getUser();
        Observable zip = Observable.zip(getUserInfo, getUser,
                new Func2<BaseHttpResult<UserInfoBean>, BaseHttpResult<UserInfoBean>, BaseHttpResult<UserInfoBean>>() {
                    @Override
                    public BaseHttpResult<UserInfoBean> call(BaseHttpResult<UserInfoBean> userInfoBeanBaseHttpResult, BaseHttpResult<UserInfoBean> userInfoBeanBaseHttpResult2) {
                        userInfoBeanBaseHttpResult.result.setUserid(userInfoBeanBaseHttpResult2.result.getUserid());
                        return userInfoBeanBaseHttpResult;
                    }
                });


        addRx(
                HttpManager.getInstance().toSubscribe(
                        loginOb.filter(booleanBaseHttpResult -> booleanBaseHttpResult.result)
                                .flatMap((Func1<BaseHttpResult<Boolean>, Observable<BaseHttpResult<UserInfoBean>>>)
                                        booleanBaseHttpResult -> zip),
                        new RxSubscriber<UserInfoBean>(mContext, true) {
                            @Override
                            protected void _onNext(UserInfoBean o) {
                                if (o != null) {
                                    o.setPhone(userName);
                                    UserInfoManager.getInstance().setCurUserInfo(o);
                                    ToastUitl.showShort("登录成功");
                                    UserChangeEvent userChangeEvent = new UserChangeEvent(UserChangeEvent.UserChangeType.LOGIN);
                                    EventBus.getDefault().post(userChangeEvent);
                                    mView.showLoginView();
                                }
                            }

                            @Override
                            protected void _onError(String message) {
                                ToastUitl.showShort("请检查手机号密码是否正确");

                            }
                        }));
    }

    @Override
    public void logout() {
        Observable observable = HttpManager.getInstance().getHttpService(ApiAccountService.class)
                .logout();
        mRxManager.add(HttpManager.getInstance().toSubscribe(observable, new RxSubscriber<Boolean>(mContext, false) {
            @Override
            protected void _onNext(Boolean o) {
                UserInfoManager.getInstance().logout();
                mView.showLogoutView();
                ToastUitl.showShort("退出登录成功");
                UserChangeEvent userChangeEvent = new UserChangeEvent(UserChangeEvent.UserChangeType.LOGOUT);
                EventBus.getDefault().post(userChangeEvent);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
