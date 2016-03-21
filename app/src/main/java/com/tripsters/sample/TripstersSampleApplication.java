package com.tripsters.sample;

import android.app.Application;
import android.content.Context;

import com.tripsters.android.TripstersApplication;

public class TripstersSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TripstersApplication.init(this);
    }
}
