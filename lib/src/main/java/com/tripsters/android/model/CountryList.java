package com.tripsters.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器返回国家列表
 */
public class CountryList extends ListNetResult<Country> {

    private List<Country> data;

    public List<Country> getList() {
        if (data == null) {
            return new ArrayList<Country>();
        }

        return data;
    }
}
