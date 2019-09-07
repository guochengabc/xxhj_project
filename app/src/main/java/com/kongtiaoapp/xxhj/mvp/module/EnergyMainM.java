package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * 获取能源管理的UBP首页
 */
public interface EnergyMainM<T> extends BaseModule {
    void getChart(Activity activity, T data, ResponseXXHJListener listener);
}
