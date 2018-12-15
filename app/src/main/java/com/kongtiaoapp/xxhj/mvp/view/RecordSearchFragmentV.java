package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * l录表查询
 */
public interface RecordSearchFragmentV<T> extends BaseView {
    void setList(T data);
    void list_null(T data);
    void error();
}
