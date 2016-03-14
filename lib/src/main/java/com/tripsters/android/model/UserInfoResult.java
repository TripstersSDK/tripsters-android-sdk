package com.tripsters.android.model;

/**
 * 服务器返回用户信息
 */
public class UserInfoResult extends NetResult {

    private UserInfo data;

    public UserInfo getUserInfo() {
        return data;
    }
}
