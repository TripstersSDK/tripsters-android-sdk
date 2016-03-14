package com.tripsters.sample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tripsters.android.model.Question;
import com.tripsters.sample.view.QuestionItemView;

public class QuestionListAdapter extends TAdapter<Question> {

    private Context mContext;
    private boolean mPortraitVisible = true;
    private boolean mAnswerVisible = true;

    public QuestionListAdapter(Context context) {
        this(context, true, true);
    }

    public QuestionListAdapter(Context context, boolean portraitVisible) {
        this(context, portraitVisible, false);
    }

    public QuestionListAdapter(Context context, boolean portraitVisible, boolean answerVisible) {
        this.mContext = context;
        this.mPortraitVisible = portraitVisible;
        this.mAnswerVisible = answerVisible;
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        }

        return mList.size();
    }

    @Override
    public Question getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuestionItemView itemView;

        if (convertView == null) {
            itemView = new QuestionItemView(mContext);
            itemView.setPortraitVisible(mPortraitVisible);
            itemView.setDetailVisible(false);
            itemView.setAnswerVisible(mAnswerVisible);
        } else {
            itemView = (QuestionItemView) convertView;
        }

        itemView.update(getItem(position));

        return itemView;
    }
}
