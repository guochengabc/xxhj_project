package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public interface AddDeviceModule<T> extends BaseModule{
    void ModifyDv(Activity activity, T data, ResponseXXHJListener listener);//修改设备信息

    void AddDv(Activity activity, T data, ResponseXXHJListener listener);//添加设备信息

}
