package com.tripsters.android.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tripsters.android.TripstersManager;

/**
 * Push信息
 */
public class Push {

    private static final String PUSH_SP = "push_sp";

    static class PushKey {
        public static final String KEY_CHANNEL_ID = "channel_id";
    }

    private static Push mInstance;

    private String mChannelId;

    private Push() {
    }

    public synchronized static Push getInstance() {
        if (mInstance == null) {
            mInstance = new Push();
        }

        return mInstance;
    }

    public void setChannelId(Context context, String channelId) {
        mChannelId = channelId;

        saveChannelIdToSp(context, channelId);
    }

    public void setChannelId(String channelId) {
        setChannelId(TripstersManager.mContext, channelId);
    }

    public String getChannelId(Context context) {
        if (TextUtils.isEmpty(mChannelId)) {
            mChannelId = getChannelIdFromSp(context);
        }

        return mChannelId;
    }

    public String getChannelId() {
        return getChannelId(TripstersManager.mContext);
    }

    public void clearChannelId(Context context) {
        setChannelId(context, "");
    }

    public void clearChannelId() {
        clearChannelId(TripstersManager.mContext);
    }

    public boolean isBind(Context context) {
        return TextUtils.isEmpty(getChannelId(context));
    }

    public boolean isBind() {
        return isBind(TripstersManager.mContext);
    }

    private static void saveChannelIdToSp(Context context, String channelId) {
        getPushSp(context).edit().putString(PushKey.KEY_CHANNEL_ID, channelId).apply();
    }

    private static String getChannelIdFromSp(Context context) {
        return getPushSp(context).getString(PushKey.KEY_CHANNEL_ID, "");
    }

    private static SharedPreferences getPushSp(Context context) {
        if (context == null) {
            return TripstersManager.mContext.getSharedPreferences(PUSH_SP, 0);
        } else {
            return context.getSharedPreferences(PUSH_SP, 0);
        }
    }
}
