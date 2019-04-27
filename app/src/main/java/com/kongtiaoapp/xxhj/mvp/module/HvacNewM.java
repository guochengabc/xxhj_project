package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

public interface HvacNewM<T> extends BaseModule {
    void getChart(Activity activity, T data,  ResponseXXHJListener lisenter);
}
