package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface HotListModule<T> extends BaseModule {
    void praise_module(Activity activity, T data, ResponseXXHJListener listener);
    void favorite_module(Activity activity, T data, ResponseXXHJListener listener);
}
