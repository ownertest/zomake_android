package com.zomake.mobile.utils;

import io.realm.Realm;

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
}
