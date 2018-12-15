package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public interface RunningRecordView<T> extends BaseView {
    void success_one();
    void paint_one(T data);
    void list_null_one();
    void error();
}
