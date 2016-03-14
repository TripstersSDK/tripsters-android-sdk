package com.tripsters.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器返回问题列表
 */
public class QuestionList extends ListNetResult<Question> {

    private List<Question> data;

    @Override
    public List<Question> getList() {
        if (data == null) {
            return new ArrayList<Question>();
        }

        return data;
    }
}
