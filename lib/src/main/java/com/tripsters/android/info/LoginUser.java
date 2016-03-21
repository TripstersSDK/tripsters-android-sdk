package com.tripsters.android.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tripsters.android.TripstersApplication;
import com.tripsters.android.model.Gender;
import com.tripsters.android.model.UserInfo;

/**
 * 当前登陆用户
 */
public class LoginUser {

    private static final String USERINFO_SP = "userinfo_sp";

    static class UserKey {
        public static final String KEY_ID = "id";
        public static final String KEY_GENDER = "gender";
        public static final String KEY_NICKNAME = "nickname";
        public static final String KEY_AVATAR = "avatar";
        public static final String KEY_IDENTITY = "identity";
        public static final String KEY_FOLLOWERS_COUNT = "followers_count";
        public static final String KEY_FRIENDS_COUNT = "friends_count";
        public static final String KEY_FANS = "fans";
        public static final String KEY_LOCATION = "location";
        public static final String KEY_DESCRIPTION = "description";
        public static final String KEY_COUNTRY = "country";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_IDLE = "idle";
        public static final String KEY_SHOW_IPADDR = "show_ipaddr";
        public static final String KEY_POINTS = "points";
        public static final String KEY_GOLD = "gold";
        public static final String KEY_GROWTH = "growth";
        public static final String KEY_NATION = "nation";
        public static final String KEY_OCCUPATION = "occupation";
        public static final String KEY_TRIP = "trip";
        public static final String KEY_FROM = "user_from";
    }

    private static LoginUser mInstance;

    private UserInfo mUserInfo;

    private LoginUser() {
    }

    public synchronized static LoginUser getInstance() {
        if (mInstance == null) {
            mInstance = new LoginUser();
        }

        return mInstance;
    }

    public boolean isLogin(Context context) {
        if (mUserInfo == null) {
            mUserInfo = getUser(context);
        }

        return mUserInfo != null;
    }

    public boolean isLogin() {
        return isLogin(TripstersApplication.mContext);
    }

    public UserInfo getUser(Context context) {
        if (mUserInfo == null) {
            mUserInfo = getUserInfoFromSp(context);
        }

        return mUserInfo;
    }

    public UserInfo getUser() {
        return getUser(TripstersApplication.mContext);
    }

    public String getId(Context context) {
        UserInfo userInfo = getUser(context);

        if (userInfo != null) {
            return userInfo.getId();
        }

        return "";
    }

    public String getId() {
        return getId(TripstersApplication.mContext);
    }

    public void setUser(Context context, final UserInfo userInfo) {
        mUserInfo = userInfo;

        saveUserInfoToSp(context, userInfo);
    }

    public void setUser(final UserInfo userInfo) {
        setUser(TripstersApplication.mContext, userInfo);
    }

    public void clearUser(Context context) {
        mUserInfo = null;

        SharedPreferences versionPrefs = getUserSp(context);
        versionPrefs.edit().clear().apply();
    }

    public void clearUser() {
        clearUser(TripstersApplication.mContext);
    }

    private static void saveUserInfoToSp(Context context, UserInfo userInfo) {
        SharedPreferences versionPrefs = getUserSp(context);
        SharedPreferences.Editor editorVersion = versionPrefs.edit();

        if (userInfo == null) {
            editorVersion.putString(UserKey.KEY_ID, "");
            editorVersion.putString(UserKey.KEY_GENDER, "");
            editorVersion.putString(UserKey.KEY_NICKNAME, "");
            editorVersion.putString(UserKey.KEY_AVATAR, "");
            editorVersion.putInt(UserKey.KEY_IDENTITY, 0);
            editorVersion.putInt(UserKey.KEY_FOLLOWERS_COUNT, 0);
            editorVersion.putInt(UserKey.KEY_FRIENDS_COUNT, 0);
            editorVersion.putInt(UserKey.KEY_FANS, 0);
            editorVersion.putString(UserKey.KEY_LOCATION, "");
            editorVersion.putString(UserKey.KEY_DESCRIPTION, "");
            editorVersion.putString(UserKey.KEY_COUNTRY, "");
            editorVersion.putString(UserKey.KEY_PHONE, "");
            editorVersion.putInt(UserKey.KEY_IDLE, 0);
            editorVersion.putString(UserKey.KEY_SHOW_IPADDR, "");
            editorVersion.putInt(UserKey.KEY_POINTS, 0);
            editorVersion.putInt(UserKey.KEY_GOLD, 0);
            editorVersion.putInt(UserKey.KEY_GROWTH, 0);
            editorVersion.putString(UserKey.KEY_NATION, "");
            editorVersion.putString(UserKey.KEY_OCCUPATION, "");
            editorVersion.putString(UserKey.KEY_TRIP, "");

            editorVersion.putString(UserKey.KEY_FROM, "");
        } else {
            editorVersion.putString(UserKey.KEY_ID, userInfo.getId());
            editorVersion.putString(UserKey.KEY_GENDER, userInfo.getGender().getValue());
            editorVersion.putString(UserKey.KEY_NICKNAME, userInfo.getNickname());
            editorVersion.putString(UserKey.KEY_AVATAR, userInfo.getAvatar());
            editorVersion.putInt(UserKey.KEY_IDENTITY, userInfo.getIdentity());
            editorVersion.putInt(UserKey.KEY_FOLLOWERS_COUNT, userInfo.getFollowersCount());
            editorVersion.putInt(UserKey.KEY_FRIENDS_COUNT, userInfo.getFriendsCount());
            editorVersion.putInt(UserKey.KEY_FANS, userInfo.getFans());
            editorVersion.putString(UserKey.KEY_LOCATION, userInfo.getLocation());
            editorVersion.putString(UserKey.KEY_DESCRIPTION, userInfo.getDescription());
            editorVersion.putString(UserKey.KEY_COUNTRY, userInfo.getCountry());
            editorVersion.putString(UserKey.KEY_PHONE, userInfo.getPhone());
            editorVersion.putInt(UserKey.KEY_IDLE, userInfo.getIdle());
            editorVersion.putString(UserKey.KEY_SHOW_IPADDR, userInfo.getShowIpaddr());
            editorVersion.putInt(UserKey.KEY_POINTS, userInfo.getPoints());
            editorVersion.putInt(UserKey.KEY_GOLD, userInfo.getGold());
            editorVersion.putInt(UserKey.KEY_GROWTH, userInfo.getGrowth());
            editorVersion.putString(UserKey.KEY_NATION, userInfo.getNation());
            editorVersion.putString(UserKey.KEY_OCCUPATION, userInfo.getOccupation());
            editorVersion.putString(UserKey.KEY_TRIP, userInfo.getTrip());

            editorVersion.putString(UserKey.KEY_FROM, userInfo.getAppid());
        }

        editorVersion.apply();
    }

    private static UserInfo getUserInfoFromSp(Context context) {
        SharedPreferences versionPrefs = getUserSp(context);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(versionPrefs.getString(UserKey.KEY_ID, ""));
        userInfo.setGender(Gender.getFromValue(versionPrefs.getString(UserKey.KEY_GENDER, "")));
        userInfo.setNickname(versionPrefs.getString(UserKey.KEY_NICKNAME, ""));
        userInfo.setAvatar(versionPrefs.getString(UserKey.KEY_AVATAR, ""));
        userInfo.setIdentity(versionPrefs.getInt(UserKey.KEY_IDENTITY, 0));
        userInfo.setFollowersCount(versionPrefs.getInt(UserKey.KEY_FOLLOWERS_COUNT, 0));
        userInfo.setFriendsCount(versionPrefs.getInt(UserKey.KEY_FRIENDS_COUNT, 0));
        userInfo.setFans(versionPrefs.getInt(UserKey.KEY_FANS, 0));
        userInfo.setLocation(versionPrefs.getString(UserKey.KEY_LOCATION, ""));
        userInfo.setDescription(versionPrefs.getString(UserKey.KEY_DESCRIPTION, ""));
        userInfo.setCountry(versionPrefs.getString(UserKey.KEY_COUNTRY, ""));
        userInfo.setPhone(versionPrefs.getString(UserKey.KEY_PHONE, ""));
        userInfo.setIdle(versionPrefs.getInt(UserKey.KEY_IDLE, 0));
        userInfo.setShowIpaddr(versionPrefs.getString(UserKey.KEY_SHOW_IPADDR, ""));
        userInfo.setPoints(versionPrefs.getInt(UserKey.KEY_POINTS, 0));
        userInfo.setGold(versionPrefs.getInt(UserKey.KEY_GOLD, 0));
        userInfo.setGrowth(versionPrefs.getInt(UserKey.KEY_GROWTH, 0));
        userInfo.setNation(versionPrefs.getString(UserKey.KEY_NATION, ""));
        userInfo.setOccupation(versionPrefs.getString(UserKey.KEY_OCCUPATION, ""));
        userInfo.setTrip(versionPrefs.getString(UserKey.KEY_TRIP, ""));

        userInfo.setAppid(versionPrefs.getString(UserKey.KEY_FROM, ""));

        if (TextUtils.isEmpty(userInfo.getId())) {
            return null;
        }

        return userInfo;
    }

    private static SharedPreferences getUserSp(Context context) {
        if (context == null) {
            return TripstersApplication.mContext.getSharedPreferences(USERINFO_SP, 0);
        } else {
            return context.getSharedPreferences(USERINFO_SP, 0);
        }
    }
}
