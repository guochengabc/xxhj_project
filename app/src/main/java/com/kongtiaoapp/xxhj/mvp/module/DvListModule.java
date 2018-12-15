package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public interface DvListModule<T> extends BaseModule {
    void deleteDevice(Activity activity, T data, ResponseXXHJListener listener);//删除设备列表
}
