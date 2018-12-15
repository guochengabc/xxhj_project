package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/9/29.  变配电数据录入详情
 */

public interface BDataEntryDetailV<T> extends BaseView {
    void setList(T data);

    void saveDrag_CommitData_View(T data);

    /**
     * 提交设备记录
     */
    void commitData(T data);
}
