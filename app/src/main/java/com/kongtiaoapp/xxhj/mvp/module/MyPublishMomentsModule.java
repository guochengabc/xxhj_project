package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public interface MyPublishMomentsModule<T> extends BaseModule {
    void praise(Activity activity, T data, ResponseXXHJListener listener);
    void favorite(Activity activity, T data, ResponseXXHJListener listener);
}
