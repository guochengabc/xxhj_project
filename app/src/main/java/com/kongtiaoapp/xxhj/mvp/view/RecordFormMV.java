package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/11/22.   记录表单模块
 */

public interface RecordFormMV<T> extends BaseView {
    void getList(T data);
}
