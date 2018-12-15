package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/9/14.  变配电  数据录入列表
 */

public interface BDataEntryListV<T> extends BaseView {
    void setList(T data);
    void list_null();
}
