package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/6/22.
 */

public interface AlarmView<T> extends BaseView {
    void setList(T data);
    void listNull();
}
