package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public interface WorkOrderActivityView<T> extends BaseView {

    void setCommit();

    void setDetailInfo(WorkOrderGet bean);

    void getStatus(String status);//得到最新状态

    void getSystem(T data);//获取系统信息

    void getLocation(T data);//获取位置信息
}
