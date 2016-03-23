package com.tripsters.sample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.tripsters.android.TripstersManager;
import com.tripsters.android.TripstersPushMessageReceiver;
import com.tripsters.sample.util.Constants;
import com.tripsters.sample.util.IntentUtils;

public class TripstersSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TripstersManager.init(this, new TripstersPushMessageReceiver.PushMessageListener() {
            @Override
            public void onPushAnswerArrived() {
                // 问题回复消息到达的处理
                IntentUtils.sendUnreadChangedBroadcast(TripstersSampleApplication.this);
            }

            @Override
            public void onPushAnswerClicked() {
                // 问题回复消息点击的处理
                onReceivedAnswerClicked(TripstersSampleApplication.this);

                IntentUtils.sendUnreadChangedBroadcast(TripstersSampleApplication.this);
            }
        });
    }

    private void onReceivedAnswerClicked(Context context) {
        Intent intent =
                new Intent(context.getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.putExtra(Constants.Extra.NOTICE_TYPE,
                MainActivity.NOTICE_RECEIVED_ANSWER);
        context.getApplicationContext().startActivity(intent);
    }
}
