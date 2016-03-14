package com.tripsters.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器返回城市列表
 */
public class CityList extends ListNetResult<City> {

    private List<City> data;

    public List<City> getList() {
        if (data == null) {
            return new ArrayList<City>();
        }

        return data;
    }
}
