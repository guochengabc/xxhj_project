package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/9/13.   变配电  报警管理
 */

public interface BAlarmV<T> extends BaseView {
    void setList(T data);

    void listNull();
}
