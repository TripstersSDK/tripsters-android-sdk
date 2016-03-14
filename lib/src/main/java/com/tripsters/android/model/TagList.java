package com.tripsters.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器返回标签列表
 */
public class TagList extends ListNetResult<Tag> {

    private List<Tag> data;

    @Override
    public List<Tag> getList() {
        if (data == null) {
            return new ArrayList<Tag>();
        }

        return data;
    }
}
