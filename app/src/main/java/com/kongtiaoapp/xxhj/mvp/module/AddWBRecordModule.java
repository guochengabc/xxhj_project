package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public interface AddWBRecordModule<T> extends BaseModule {
    void save(Activity activity, T data, ResponseXXHJListener listener);
}
