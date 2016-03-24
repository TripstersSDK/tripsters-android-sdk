package com.tripsters.android;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.tripsters.android.info.LoginUser;
import com.tripsters.android.info.MessageUnread;
import com.tripsters.android.info.Push;
import com.tripsters.android.info.SelectCountry;
import com.tripsters.android.model.UserInfo;

public class TripstersManager {

    public static Context mContext;
    private static boolean isPushRegistered;

    /**
     * 初始化mContext为全局上下文，可以让sdk方便使用全局上下文
     *
     * @param application 全局上下文
     */
    public static void init(Application application) {
        mContext = application.getApplicationContext();
        isPushRegistered = false;
    }

    /**
     * 初始化并注册百度push
     *
     * @param application 全局上下文
     * @param listener    百度push回调
     */
    public static void init(Application application,
                            TripstersPushMessageReceiver.PushMessageListener listener) {
        mContext = application.getApplicationContext();
        isPushRegistered = true;

        // 注册百度push
        TripstersPushMessageReceiver.start(mContext, listener);
    }

    public static void clear() {
        if (isPushRegistered) {
            TripstersPushMessageReceiver.stop(mContext);
        }

        LoginUser.getInstance().clearUser();
        SelectCountry.getInstance().clearCountry();
        MessageUnread.getInstance().clearUnread();
    }

    /**
     * 更新push信息(在用户登陆成功后调用)
     *
     * @param userInfo 用户资料
     */
    public static void updateUserInfo(UserInfo userInfo) {
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getId())) {
            if (Push.getInstance().isBind()) {
                TripstersPushMessageReceiver.updateUserInfo(userInfo.getId(),
                        Push.getInstance().getChannelId());
            }
        }
    }
}
