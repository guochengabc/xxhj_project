package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public interface ZhiBanDetailModule<T> extends BaseModule {
    void getLatePassWork(Activity activity, T data, ResponseXXHJListener listener);//最近交班信息
    void getLateTakeWork(Activity activity, T data, ResponseXXHJListener listener);//最近接班信息
    void submit(Activity activity, T data, ResponseXXHJListener listener);
}
