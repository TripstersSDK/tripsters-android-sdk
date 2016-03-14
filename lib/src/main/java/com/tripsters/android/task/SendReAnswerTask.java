package com.tripsters.android.task;

import android.content.Context;
import android.os.AsyncTask;

import com.tripsters.android.model.NetResult;
import com.tripsters.android.net.NetRequest;

import java.io.IOException;

/**
 * sendReAnswerById的封装task，用于同步调用
 * @see NetRequest#sendReAnswerById
 */
public class SendReAnswerTask extends AsyncTask<Void, Void, NetResult> {

    /**
     * sendReAnswerById的同步返回调用
     */
    public interface SendReAnswerTaskResult {
        void onTaskResult(NetResult result);
    }

    private Context mContext;
    private String mUid;
    private String mDetail;
    private String mPicPath;
    private String mQid;
    private String mAuid;
    private SendReAnswerTaskResult mTaskResult;

    public SendReAnswerTask(Context context, String uid, String detail, String picPath, String qid, String auid,
                            SendReAnswerTaskResult taskResult) {
        this.mContext = context;
        this.mUid = uid;
        this.mDetail = detail;
        this.mPicPath = picPath;
        this.mQid = qid;
        this.mAuid = auid;
        this.mTaskResult = taskResult;
    }

    @Override
    protected NetResult doInBackground(Void... params) {
        try {
            return NetRequest.sendReAnswerById(mContext, mUid, mDetail, mPicPath, mQid, mAuid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(NetResult result) {
        mTaskResult.onTaskResult(result);
    }
}
