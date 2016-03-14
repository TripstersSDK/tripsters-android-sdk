package com.tripsters.android.model;

import java.util.List;

/**
 * 服务器返回列表父类
 *
 * @param <T>
 */
public abstract class ListNetResult<T> extends NetResult {
    public abstract List<T> getList();
}
