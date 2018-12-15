package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public interface RunningDeviceListModule<T> extends BaseModule {
    void changeToken(Activity activity, T data, ResponseXXHJListener listener);

    void submit_device(Activity activity, T data, ResponseXXHJListener listener);
}
