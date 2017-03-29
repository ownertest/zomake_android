package com.zomake.mobile.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.baserx.RxCountDown;
import com.jaydenxiao.common.commonutils.FormatUtil;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.zomake.mobile.R;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class RegisterFragment extends BaseFragment<RegisterPresenter> implements BaseContract.IRegisterView {
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

    }

    @Override
    protected void initView() {

    }

    @Override
    public void showRegisterView() {

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
            Toast.makeText(getActivity(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        mPresenter.sendSmsCode(userName);
    }

    @OnClick(R.id.btn_register)
    public void registerClick() {
        String userName = mEtPhone.getText().toString();
        if (!FormatUtil.isMobileNO(userName)) {
            Toast.makeText(getActivity(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = mEtCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(getActivity(), "请输入手机验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
            Toast.makeText(getActivity(), "请输入正确的密码", Toast.LENGTH_SHORT).show();
            return;
        }

        mPresenter.register(userName, pwd, code);
    }

}
