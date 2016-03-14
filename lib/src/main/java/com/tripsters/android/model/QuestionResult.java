package com.tripsters.android.model;

/**
 * 服务器返回问题
 */
public class QuestionResult extends NetResult {

    private Question data;

    public Question getQuestion() {
        return data;
    }
}
