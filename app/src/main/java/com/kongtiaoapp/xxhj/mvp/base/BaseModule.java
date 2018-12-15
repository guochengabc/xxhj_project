package com.kongtiaoapp.xxhj.mvp.base;

import android.app.Activity;

import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/4/25.
 */
public interface BaseModule<V> {
    void getDataForservices(Activity activity, V data, ResponseXXHJListener listener);
}
