package com.tripsters.android.task;

import android.content.Context;
import android.os.AsyncTask;

import com.tripsters.android.model.UserInfoResult;
import com.tripsters.android.net.NetRequest;

import java.io.IOException;

/**
 * getUserInfo的封装task，用于同步调用
 * @see NetRequest#getUserInfo
 */
public class GetUserInfoTask extends AsyncTask<Void, Void, UserInfoResult> {

    /**
     * getUserInfo的同步返回调用
     */
    public interface GetUserInfoTaskResult {
        void onTaskResult(UserInfoResult result);
    }

    private Context mContext;
    private String mUid;
    private GetUserInfoTaskResult mTaskResult;

    public GetUserInfoTask(Context context, String uid, GetUserInfoTaskResult taskResult) {
        this.mContext = context;
        this.mUid = uid;
        this.mTaskResult = taskResult;
    }

    @Override
    protected UserInfoResult doInBackground(Void... params) {
        try {
            return NetRequest.getUserInfo(mContext, mUid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(UserInfoResult result) {
        if (mTaskResult != null) {
            mTaskResult.onTaskResult(result);
        }
    }
}
