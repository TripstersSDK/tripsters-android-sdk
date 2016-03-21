package com.tripsters.android.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tripsters.android.TripstersApplication;
import com.tripsters.android.model.Country;

/**
 * 当前选择国家
 */
public class SelectCountry {

    private static final String COUNTRY_SP = "country_sp";

    static class CountryKey {
        public static final String KEY_ID = "id";
        public static final String KEY_COUNTRY_NAME_CN = "country_name_cn";
        public static final String KEY_COUNTRY_NAME_EN = "country_name_en";
        public static final String KEY_COUNTRY_NAME_LOCAL = "country_name_local";
        public static final String KEY_COUNTRY_CODE = "country_code";
        public static final String KEY_PIC = "pic";
        public static final String KEY_HOT = "hot";
    }

    private static SelectCountry mInstance;

    private Country mCountry;

    private SelectCountry() {
    }

    public synchronized static SelectCountry getInstance() {
        if (mInstance == null) {
            mInstance = new SelectCountry();
        }

        return mInstance;
    }

    public Country getCountry(Context context) {
        if (mCountry == null) {
            mCountry = getChangeCountryFromSp(context);
        }

        return mCountry;
    }

    public Country getCountry() {
        return getCountry(TripstersApplication.mContext);
    }

    public void setCountry(Context context, final Country country) {
        mCountry = country;

        saveChangeCountryToSp(context, country);
    }

    public void setCountry(final Country country) {
        setCountry(TripstersApplication.mContext, country);
    }

    private static void saveChangeCountryToSp(Context context, Country country) {
        SharedPreferences sp = getCountrySp(context);
        SharedPreferences.Editor editor = sp.edit();

        if (country == null) {
            editor.putInt(CountryKey.KEY_ID, 0);
            editor.putString(CountryKey.KEY_COUNTRY_NAME_CN, "");
            editor.putString(CountryKey.KEY_COUNTRY_NAME_EN, "");
            editor.putString(CountryKey.KEY_COUNTRY_NAME_LOCAL, "");
            editor.putString(CountryKey.KEY_COUNTRY_CODE, "");
            editor.putString(CountryKey.KEY_PIC, "");
            editor.putInt(CountryKey.KEY_HOT, 0);
        } else {
            editor.putInt(CountryKey.KEY_ID, country.getId());
            editor.putString(CountryKey.KEY_COUNTRY_NAME_CN, country.getCountryNameCn());
            editor.putString(CountryKey.KEY_COUNTRY_NAME_EN, country.getCountryNameEn());
            editor.putString(CountryKey.KEY_COUNTRY_NAME_LOCAL, country.getCountryNameLocal());
            editor.putString(CountryKey.KEY_COUNTRY_CODE, country.getCountryCode());
            editor.putString(CountryKey.KEY_PIC, country.getPic());
            editor.putInt(CountryKey.KEY_HOT, country.getHot());
        }

        editor.apply();
    }

    private static Country getChangeCountryFromSp(Context context) {
        Country country = new Country();

        SharedPreferences sp = getCountrySp(context);
        country.setId(sp.getInt(CountryKey.KEY_ID, 0));
        country.setCountryNameCn(sp.getString(CountryKey.KEY_COUNTRY_NAME_CN, ""));
        country.setCountryNameEn(sp.getString(CountryKey.KEY_COUNTRY_NAME_EN, ""));
        country.setCountryNameLocal(sp.getString(CountryKey.KEY_COUNTRY_NAME_LOCAL, ""));
        country.setCountryCode(sp.getString(CountryKey.KEY_COUNTRY_CODE, ""));
        country.setPic(sp.getString(CountryKey.KEY_PIC, ""));
        country.setHot(sp.getInt(CountryKey.KEY_HOT, 0));

        if (country.getId() == 0 && TextUtils.isEmpty(country.getCountryCode())) {
            return null;
        }

        return country;
    }

    private static SharedPreferences getCountrySp(Context context) {
        if (context == null) {
            return TripstersApplication.mContext.getSharedPreferences(COUNTRY_SP, 0);
        } else {
            return context.getSharedPreferences(COUNTRY_SP, 0);
        }
    }
}
