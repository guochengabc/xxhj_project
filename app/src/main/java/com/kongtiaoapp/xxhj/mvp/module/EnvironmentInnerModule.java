package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

public interface EnvironmentInnerModule<T> extends BaseModule {
    /**
     * 获取环境监测首页信息
     */
    void EnvironmentInnerInfo(Activity activity, T data, ResponseXXHJListener listener);

    /**
     * 获取环境监测图表信息
     */
    void EnvironmentInnerPaint(Activity activity, T data, ResponseXXHJListener listener);
}
