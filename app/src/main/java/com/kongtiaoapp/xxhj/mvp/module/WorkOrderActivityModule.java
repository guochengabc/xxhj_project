package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public interface WorkOrderActivityModule<T,M> extends BaseModule {
    void getCommit(Activity activity, T t, M listSysLocation, int size, ResponseXXHJListener listener);//提交工单

    void getRepairStatus(Activity activity, String param, ResponseXXHJListener listener);

    void getSystem(Activity activity, ResponseXXHJListener listener);//获取系统

    void getLocation(Activity activity, String whichType, ResponseXXHJListener listener);//获取楼栋、楼层、位置
}
