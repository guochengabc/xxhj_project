package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/18.
 */
public interface RunningRecord_DutyView<T> extends BaseView {
    void setList(T data);

    void list_null();

    void error();
}
