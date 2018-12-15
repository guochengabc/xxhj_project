package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/11/22.  工单统计
 */

public interface WorkOrderSatisficModule<T> extends BaseModule {
    void getEngineerRecordForm(Activity activity, T data, ResponseXXHJListener listener);
    void getRepairRecordForm(Activity activity, T data, ResponseXXHJListener listener);
}
