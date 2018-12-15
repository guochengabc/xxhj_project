package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public interface MessageView<T> extends BaseView {
    void setList(T data);
    void list_null();
    void error();
}
