package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * 录表查询
 */
public interface RecordSearchDetailsV<T> extends BaseView {
    /**
     * 获取之前提交的数据
     */
    void getList(T data);

    void list_null();

    void list_error();

    /**
     * 修改数据
     */
    void modifyData();

    /**
     * 撤回数据
     */
    void recallData();
}
