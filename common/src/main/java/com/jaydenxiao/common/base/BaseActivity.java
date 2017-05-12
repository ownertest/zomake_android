package com.jaydenxiao.common.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.baseapp.AppManager;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.TUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.jaydenxiao.common.daynightmodeutils.ChangeModeController;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T mPresenter;
    public Context mContext;
    public RxManager mRxManager;
    private View mCommonActionBar;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetContentView();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initActionBar();
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            mPresenter.mContext = this;
            if (this instanceof BaseView)
                mPresenter.setVM(this);
        }

        this.initData(getIntent().getExtras());
        this.initPresenter();
        this.initView();
    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar() {
        mCommonActionBar = LayoutInflater.from(this).inflate(
                R.layout.base_title_bar, null);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayShowCustomEnabled(true);
            mActionBar.setDisplayShowTitleEnabled(false);
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setElevation(0);
            mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            mActionBar.setBackgroundDrawable(getResources().getDrawable(
                    R.drawable.white_drawable));
            mActionBar.setCustomView(mCommonActionBar);
            if (isShowTitleBar())
                mActionBar.show();
            else
                mActionBar.hide();
        }
    }

    /**
     * 标题栏布局.
     */
    private NormalTitleBar mTitleBar = null;

    public NormalTitleBar getTitleBar() {
        if (mTitleBar == null) {
            mTitleBar = (NormalTitleBar) mCommonActionBar.findViewById(R.id.title_bar);
        }
        if (mTitleBar == null) {
            mTitleBar = new NormalTitleBar(this);
            mTitleBar.setVisibility(View.GONE);
        }
        return mTitleBar;
    }

    protected boolean isShowTitleBar() {
        return true;
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetContentView() {
        //设置昼夜主题
        initTheme();
        // 把activity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
//        SetTranslanteBar();

    }

    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initView();

    //获取 bundle 数据
    public void initData(Bundle extras) {

    }


    /**
     * 设置主题
     */
    private void initTheme() {
        int dayTheme = isShowTitleBar() ? R.style.DayForBarTheme : R.style.DayTheme;
        int nightTheme = isShowTitleBar() ? R.style.NightForBarTheme : R.style.NightTheme;
        ChangeModeController.setTheme(this, dayTheme, nightTheme);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.white));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(), R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //debug版本不统计crash
//        if (!BuildConfig.LOG_DEBUG) {
//            //友盟统计
//            MobclickAgent.onResume(this);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //debug版本不统计crash
//        if (!BuildConfig.LOG_DEBUG) {
//            //友盟统计
//            MobclickAgent.onPause(this);
//        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
        mUnbinder.unbind();
        AppManager.getAppManager().finishActivity(this);
    }
}
