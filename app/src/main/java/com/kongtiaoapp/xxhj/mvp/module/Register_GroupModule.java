package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public interface Register_GroupModule<T> extends BaseModule {
    void sendIdentify(Activity activity, T data, ResponseXXHJListener listener);
}
