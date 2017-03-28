package com.zomake.mobile.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseHttpResult;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.zomake.mobile.R;
import com.zomake.mobile.api.ApiAccountService;
import com.zomake.mobile.api.HttpManager;
import com.zomake.mobile.bean.UserInfoBean;
import com.zomake.mobile.contract.BaseContract;
import com.zomake.mobile.ui.Presenter.LoginPresenter;
import com.zomake.mobile.utils.DeviceUtil;
import com.zomake.mobile.utils.UserInfoManager;
import com.zomake.mobile.widget.StateButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class SettingActivity extends BaseActivity<LoginPresenter> implements BaseContract.ILoginView {


    @BindView(R.id.clear)
    TextView mClear;
    @BindView(R.id.tv_comment)
    TextView mTvComment;
    @BindView(R.id.tv_lang)
    TextView mTvLang;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(R.id.bt_log_out)
    StateButton mBtLogOut;
    @BindView(R.id.activity_setting)
    LinearLayout mActivitySetting;

    @OnClick(R.id.bt_log_out)
    public void logoutClick() {
        if (UserInfoManager.getInstance().isLogin())
            mPresenter.logout();
        else
            mPresenter.login("18511621123", "wn890331");
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this);
    }

    @Override
    public void initView() {
        String txt = UserInfoManager.getInstance().isLogin() ? "退出登录" : "立即登录";
        int color = UserInfoManager.getInstance().isLogin() ? R.color.red : R.color.black;
        mBtLogOut.setNormalStrokeColor(getResources().getColor(color));
        mBtLogOut.setNormalTextColor(getResources().getColor(color));
        mBtLogOut.setText(txt);

    }

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
    public void showLoginView() {
        mBtLogOut.setText("退出登录");
        mBtLogOut.setNormalTextColor(getResources().getColor(R.color.red));
        mBtLogOut.setNormalStrokeColor(getResources().getColor(R.color.red));
    }

    @Override
    public void showLogoutView() {
        mBtLogOut.setText("立即登录");
        mBtLogOut.setNormalTextColor(getResources().getColor(R.color.black));
        mBtLogOut.setNormalStrokeColor(getResources().getColor(R.color.black));
    }
}
