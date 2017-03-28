package com.zomake.mobile.utils;

import com.zomake.mobile.bean.UserInfoBean;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wojiushiwn on 2017/3/24.
 * desc:
 */

public class RealmHelper {


    public static final String DB_NAME = "zomake.realm";
    private Realm mRealm;
    private static RealmHelper instance;

    private RealmHelper() {
    }

    public static RealmHelper getInstance() {
        if (instance == null) {
            synchronized (RealmHelper.class) {
                if (instance == null)
                    instance = new RealmHelper();
            }
        }
        return instance;
    }


    protected Realm getRealm() {
        if (mRealm == null || mRealm.isClosed())
            mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    void insertUserInfo(UserInfoBean userInfoBean) {
        getRealm().beginTransaction();
        getRealm().copyToRealmOrUpdate(userInfoBean);
        getRealm().commitTransaction();
    }

    UserInfoBean getUserInfo() {
        //使用findAllSort ,先findAll再result.sort排序
        UserInfoBean results = getRealm().where(UserInfoBean.class).
                findFirst();
        if (results != null)
            return getRealm().copyFromRealm(results);

        return null;
    }

    void deleteUserInfo() {
        getRealm().beginTransaction();
        getRealm().delete(UserInfoBean.class);
        getRealm().commitTransaction();
    }
}
