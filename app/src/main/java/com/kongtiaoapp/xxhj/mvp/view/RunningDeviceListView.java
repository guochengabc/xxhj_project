package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public interface RunningDeviceListView<T> extends BaseView {
    void changeToken(T data);

    void submit_View();
}