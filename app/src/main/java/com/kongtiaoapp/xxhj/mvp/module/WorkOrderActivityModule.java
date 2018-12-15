package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public interface WorkOrderActivityModule<T> extends BaseModule {
    void getCommit(Activity activity, T t, int size, ResponseXXHJListener listener);//提交工单
    void getRepairStatus(Activity activity, String param, ResponseXXHJListener listener);
}
