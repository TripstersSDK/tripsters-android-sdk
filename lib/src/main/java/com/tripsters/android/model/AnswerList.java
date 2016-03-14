package com.tripsters.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器返回问题列表
 */
public class AnswerList extends ListNetResult<Answer> {

    private List<Answer> data;

    @Override
    public List<Answer> getList() {
        if (data == null) {
            return new ArrayList<Answer>();
        }

        return data;
    }
}
