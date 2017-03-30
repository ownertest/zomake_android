package com.zomake.mobile.ui.fragment;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.FormatUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.DrawableCenterTextView;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.zomake.mobile.R;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements BaseContract.ILoginView {
    @BindView(R.id.et_phone)
    TextInputEditText mEtPhone;
    @BindView(R.id.tv_pwd_icon)
    FontTextView mTvPwdIcon;
    @BindView(R.id.et_pwd)
    TextInputEditText mEtPwd;
    @BindView(R.id.tv_forget)
    TextView mTvForget;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_wechat)
    DrawableCenterTextView mBtnWechat;
    @BindView(R.id.btn_facebook)
    DrawableCenterTextView mBtnFacebook;

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void showLoginView() {
        getActivity().finish();
    }

    @Override
    public void showLogoutView() {

    }

    @OnClick(R.id.btn_login)
    public void loginClick() {
        String phone = mEtPhone.getText().toString();
        if (TextUtils.isEmpty(phone) || !FormatUtil.isMobileNO(mEtPhone.getText().toString())) {
            ToastUitl.showShort("请输入正确的手机号");
            return;
        }
        String pwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
            ToastUitl.showShort("请输入正确的密码");
            return;
        }

        mPresenter.login(phone, pwd);

    }
}
