package com.tripsters.android.task;

import android.content.Context;
import android.os.AsyncTask;

import com.tripsters.android.model.QuestionList;
import com.tripsters.android.net.NetRequest;

import java.io.IOException;

/**
 * getAppQuestion的封装task，用于同步调用
 * @see NetRequest#getAppQuestion
 */
public class GetAppQuestionTask extends AsyncTask<Void, Void, QuestionList> {

    /**
     * getAppQuestion的同步返回调用
     */
    public interface GetAppQuestionTaskResult {
        void onTaskResult(QuestionList result);
    }

    private Context mContext;
    private String mCountryName;
    private int mPage;
    private int mPageCount;
    private GetAppQuestionTaskResult mTaskResult;

    public GetAppQuestionTask(Context context, String countryName, int page, int pageCount,
                              GetAppQuestionTaskResult taskResult) {
        this.mContext = context;
        this.mCountryName = countryName;
        this.mPage = page;
        this.mPageCount = pageCount;
        this.mTaskResult = taskResult;
    }

    @Override
    protected QuestionList doInBackground(Void... params) {
        try {
            return NetRequest.getAppQuestion(mContext, mCountryName, mPage, mPageCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(QuestionList result) {
        if (mTaskResult != null) {
            mTaskResult.onTaskResult(result);
        }
    }
}
