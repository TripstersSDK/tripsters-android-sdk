package com.tripsters.sample.fragment;

import com.tripsters.android.TripstersManager;
import com.tripsters.android.info.SelectCountry;
import com.tripsters.android.model.QuestionList;
import com.tripsters.android.task.GetAppQuestionTask;
import com.tripsters.sample.util.Constants;

public class AppQuestionListFragment extends QuestionListFragment {

    @Override
    protected void loadData(int page) {
        new GetAppQuestionTask(TripstersManager.mContext, SelectCountry.getInstance().getCountry().getCountryNameCn(),
                page, Constants.PAGE_COUNT, new GetAppQuestionTask.GetAppQuestionTaskResult() {
            @Override
            public void onTaskResult(QuestionList result) {
                setResultInfo(result);
            }
        }).execute();
    }
}
