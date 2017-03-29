package com.zomake.mobile.event;

import com.zomake.mobile.bean.UserInfoBean;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class UserChangeEvent {

    public enum UserChangeType {
        LOGIN, LOGOUT, MODIFY
    }

    private UserChangeType type;

    public UserChangeEvent(UserChangeType type) {
        this.type = type;
    }

    public UserChangeType getType() {
        return type;
    }
}
