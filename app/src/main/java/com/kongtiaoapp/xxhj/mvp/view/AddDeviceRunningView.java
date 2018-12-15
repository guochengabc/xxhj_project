package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public interface AddDeviceRunningView<T> extends BaseView {
    void setList(T data);

    void saveDrag_CommitData_View(T data);

    /**
     * 提交设备记录
     */
    void commitData(T data);
}
