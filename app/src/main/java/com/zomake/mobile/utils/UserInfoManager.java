package com.zomake.mobile.utils;

import android.text.TextUtils;

import com.zomake.mobile.bean.UserInfoBean;

/**
 * Created by wojiushiwn on 2017/3/28.
 * desc:
 */

public class UserInfoManager {

    private static UserInfoManager mUserManager;

    public static UserInfoManager getInstance() {
        if (mUserManager == null) {
            synchronized (UserInfoManager.class) {
                if (mUserManager == null)
                    mUserManager = new UserInfoManager();
            }
        }
        return mUserManager;
    }

    private UserInfoManager() {
        if (getCurUserInfo() == null) {
            setCurUserInfo(readUserInfoByDb());
        }
    }

    private UserInfoBean mCurUserInfo;


    public UserInfoBean getCurUserInfo() {
        return mCurUserInfo;
    }

    public void setCurUserInfo(UserInfoBean curUserInfo) {
        if (curUserInfo == null) return;
        mCurUserInfo = curUserInfo;
        saveToDb(mCurUserInfo);
    }

    private void saveToDb(UserInfoBean userInfoBean) {
        RealmHelper.getInstance().insertUserInfo(userInfoBean);
    }

    private UserInfoBean readUserInfoByDb() {
        return RealmHelper.getInstance().getUserInfo();
    }

    public boolean isLogin() {
        return mCurUserInfo != null && !TextUtils.isEmpty(mCurUserInfo.getUserid());
    }

    public void logout() {
        mCurUserInfo = null;
        RealmHelper.getInstance().deleteUserInfo();
    }
}
