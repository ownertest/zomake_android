package com.zomake.mobile.ui.fragment;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.baserx.RxCountDown;
import com.jaydenxiao.common.commonutils.FormatUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.zomake.mobile.R;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.LoginPresenter;
import com.zomake.mobile.ui.Presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class RegisterFragment extends BaseFragment<RegisterPresenter>
        implements BaseContract.IRegisterView, BaseContract.ILoginView {
    @BindView(R.id.et_phone)
    TextInputEditText mEtPhone;
    @BindView(R.id.et_code)
    TextInputEditText mEtCode;
    @BindView(R.id.tv_sendcode)
    TextView mTvSendcode;
    @BindView(R.id.et_pwd)
    TextInputEditText mEtPwd;
    @BindView(R.id.tv_show_pwd)
    FontTextView mTvShowPwd;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    private LoginPresenter mLoginPresenter;

    boolean isShowPwd = false;

    @Override
    public void showLoading(String title) {
        startProgressDialog(title);
    }

    @Override
    public void stopLoading() {
        stopProgressDialog();
    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_register;
    }

    @Override
    public void initPresenter() {
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.setVM(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void showRegisterView(String userName, String pwd) {
        mLoginPresenter.login(userName, pwd);
    }

    @Override
    public void showSendCodeView() {
        mRxManager.add(RxCountDown.countdown(30)
                .doOnSubscribe(() -> mTvSendcode.setEnabled(false))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        mTvSendcode.setEnabled(true);
                        mTvSendcode.setText("发送验证码");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        mTvSendcode.setText(String.format("%d s", integer));
                    }
                }));
    }

    @OnClick(R.id.tv_show_pwd)
    public void switchShowPwdClick() {
        isShowPwd = !isShowPwd;

        mEtPwd.setTransformationMethod(!isShowPwd ?
                PasswordTransformationMethod.getInstance() :
                HideReturnsTransformationMethod.getInstance());

        String fontIcon = isShowPwd ? "\ue689" : "\ue68b";
        mTvShowPwd.setText(fontIcon);
    }

    @OnClick(R.id.tv_sendcode)
    public void sendCodeClick() {
        String userName = mEtPhone.getText().toString();
        if (!FormatUtil.isMobileNO(userName)) {
            ToastUitl.showShort("请输入正确的手机号");
            return;
        }

        mPresenter.sendSmsCode(userName);
    }

    @OnClick(R.id.btn_register)
    public void registerClick() {
        String userName = mEtPhone.getText().toString();
        if (!FormatUtil.isMobileNO(userName)) {
            ToastUitl.showShort("请输入正确的手机号");
            return;
        }

        String code = mEtCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUitl.showShort("请输入手机验证码");
            return;
        }

        String pwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
            ToastUitl.showShort("请输入正确的密码");
            return;
        }

        mPresenter.register(userName, pwd, code);
    }

    @Override
    public void showLoginView() {
        getActivity().finish();
    }

    @Override
    public void showLogoutView() {

    }
}
