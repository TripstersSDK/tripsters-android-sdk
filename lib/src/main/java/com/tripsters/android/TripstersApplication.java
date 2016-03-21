package com.tripsters.android;

import android.app.Application;
import android.content.Context;

public class TripstersApplication extends Application {

    public static Context mContext;

    public static void init(Application application) {
        mContext = application.getApplicationContext();
    }
}
