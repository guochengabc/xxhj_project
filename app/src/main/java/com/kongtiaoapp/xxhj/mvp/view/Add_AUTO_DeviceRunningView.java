package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/7/11.
 */

public interface Add_AUTO_DeviceRunningView<T> extends BaseView {
    void setListPaint(T list);
    void setList_nullPaint();
    void setList_error();
    void setList(T list);
    void saveDrag_CommitData_View(T data);
    void showPaint(T bean);
    void list_null();
    void errorlist();

}
