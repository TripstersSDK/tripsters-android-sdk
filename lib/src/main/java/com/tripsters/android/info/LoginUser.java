package com.tripsters.android.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tripsters.android.TripstersManager;
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
        public static final String KEY_APPID = "appid";
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
        return isLogin(TripstersManager.mContext);
    }

    public UserInfo getUser(Context context) {
        if (mUserInfo == null) {
            mUserInfo = getUserInfoFromSp(context);
        }

        return mUserInfo;
    }

    public UserInfo getUser() {
        return getUser(TripstersManager.mContext);
    }

    public String getId(Context context) {
        UserInfo userInfo = getUser(context);

        if (userInfo != null) {
            return userInfo.getId();
        }

        return "";
    }

    public String getId() {
        return getId(TripstersManager.mContext);
    }

    public void setUser(Context context, final UserInfo userInfo) {
        mUserInfo = userInfo;

        saveUserInfoToSp(context, userInfo);
    }

    public void setUser(final UserInfo userInfo) {
        setUser(TripstersManager.mContext, userInfo);
    }

    public void clearUser(Context context) {
        mUserInfo = null;

        SharedPreferences sharedPreferences = getUserSp(context);
        sharedPreferences.edit().clear().apply();
    }

    public void clearUser() {
        clearUser(TripstersManager.mContext);
    }

    private static void saveUserInfoToSp(Context context, UserInfo userInfo) {
        SharedPreferences sharedPreferences = getUserSp(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (userInfo == null) {
            editor.putString(UserKey.KEY_ID, "");
            editor.putString(UserKey.KEY_GENDER, "");
            editor.putString(UserKey.KEY_NICKNAME, "");
            editor.putString(UserKey.KEY_AVATAR, "");
            editor.putInt(UserKey.KEY_IDENTITY, 0);
            editor.putInt(UserKey.KEY_FOLLOWERS_COUNT, 0);
            editor.putInt(UserKey.KEY_FRIENDS_COUNT, 0);
            editor.putInt(UserKey.KEY_FANS, 0);
            editor.putString(UserKey.KEY_LOCATION, "");
            editor.putString(UserKey.KEY_DESCRIPTION, "");
            editor.putString(UserKey.KEY_COUNTRY, "");
            editor.putString(UserKey.KEY_PHONE, "");
            editor.putInt(UserKey.KEY_IDLE, 0);
            editor.putString(UserKey.KEY_SHOW_IPADDR, "");
            editor.putInt(UserKey.KEY_POINTS, 0);
            editor.putInt(UserKey.KEY_GOLD, 0);
            editor.putInt(UserKey.KEY_GROWTH, 0);
            editor.putString(UserKey.KEY_NATION, "");
            editor.putString(UserKey.KEY_OCCUPATION, "");
            editor.putString(UserKey.KEY_TRIP, "");

            editor.putString(UserKey.KEY_APPID, "");
        } else {
            editor.putString(UserKey.KEY_ID, userInfo.getId());
            editor.putString(UserKey.KEY_GENDER, userInfo.getGender().getValue());
            editor.putString(UserKey.KEY_NICKNAME, userInfo.getNickname());
            editor.putString(UserKey.KEY_AVATAR, userInfo.getAvatar());
            editor.putInt(UserKey.KEY_IDENTITY, userInfo.getIdentity());
            editor.putInt(UserKey.KEY_FOLLOWERS_COUNT, userInfo.getFollowersCount());
            editor.putInt(UserKey.KEY_FRIENDS_COUNT, userInfo.getFriendsCount());
            editor.putInt(UserKey.KEY_FANS, userInfo.getFans());
            editor.putString(UserKey.KEY_LOCATION, userInfo.getLocation());
            editor.putString(UserKey.KEY_DESCRIPTION, userInfo.getDescription());
            editor.putString(UserKey.KEY_COUNTRY, userInfo.getCountry());
            editor.putString(UserKey.KEY_PHONE, userInfo.getPhone());
            editor.putInt(UserKey.KEY_IDLE, userInfo.getIdle());
            editor.putString(UserKey.KEY_SHOW_IPADDR, userInfo.getShowIpaddr());
            editor.putInt(UserKey.KEY_POINTS, userInfo.getPoints());
            editor.putInt(UserKey.KEY_GOLD, userInfo.getGold());
            editor.putInt(UserKey.KEY_GROWTH, userInfo.getGrowth());
            editor.putString(UserKey.KEY_NATION, userInfo.getNation());
            editor.putString(UserKey.KEY_OCCUPATION, userInfo.getOccupation());
            editor.putString(UserKey.KEY_TRIP, userInfo.getTrip());

            editor.putString(UserKey.KEY_APPID, userInfo.getAppid());
        }

        editor.apply();
    }

    private static UserInfo getUserInfoFromSp(Context context) {
        SharedPreferences sharedPreferences = getUserSp(context);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(sharedPreferences.getString(UserKey.KEY_ID, ""));
        userInfo.setGender(Gender.getFromValue(sharedPreferences.getString(UserKey.KEY_GENDER, "")));
        userInfo.setNickname(sharedPreferences.getString(UserKey.KEY_NICKNAME, ""));
        userInfo.setAvatar(sharedPreferences.getString(UserKey.KEY_AVATAR, ""));
        userInfo.setIdentity(sharedPreferences.getInt(UserKey.KEY_IDENTITY, 0));
        userInfo.setFollowersCount(sharedPreferences.getInt(UserKey.KEY_FOLLOWERS_COUNT, 0));
        userInfo.setFriendsCount(sharedPreferences.getInt(UserKey.KEY_FRIENDS_COUNT, 0));
        userInfo.setFans(sharedPreferences.getInt(UserKey.KEY_FANS, 0));
        userInfo.setLocation(sharedPreferences.getString(UserKey.KEY_LOCATION, ""));
        userInfo.setDescription(sharedPreferences.getString(UserKey.KEY_DESCRIPTION, ""));
        userInfo.setCountry(sharedPreferences.getString(UserKey.KEY_COUNTRY, ""));
        userInfo.setPhone(sharedPreferences.getString(UserKey.KEY_PHONE, ""));
        userInfo.setIdle(sharedPreferences.getInt(UserKey.KEY_IDLE, 0));
        userInfo.setShowIpaddr(sharedPreferences.getString(UserKey.KEY_SHOW_IPADDR, ""));
        userInfo.setPoints(sharedPreferences.getInt(UserKey.KEY_POINTS, 0));
        userInfo.setGold(sharedPreferences.getInt(UserKey.KEY_GOLD, 0));
        userInfo.setGrowth(sharedPreferences.getInt(UserKey.KEY_GROWTH, 0));
        userInfo.setNation(sharedPreferences.getString(UserKey.KEY_NATION, ""));
        userInfo.setOccupation(sharedPreferences.getString(UserKey.KEY_OCCUPATION, ""));
        userInfo.setTrip(sharedPreferences.getString(UserKey.KEY_TRIP, ""));

        userInfo.setAppid(sharedPreferences.getString(UserKey.KEY_APPID, ""));

        if (TextUtils.isEmpty(userInfo.getId())) {
            return null;
        }

        return userInfo;
    }

    private static SharedPreferences getUserSp(Context context) {
        if (context == null) {
            return TripstersManager.mContext.getSharedPreferences(USERINFO_SP, 0);
        } else {
            return context.getSharedPreferences(USERINFO_SP, 0);
        }
    }
}
