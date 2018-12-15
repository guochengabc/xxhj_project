package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/9.
 */
public interface Protect_PaintModule<T> extends BaseModule {
    void getDataForServers_two(Activity activity, T data, ResponseXXHJListener listener);
}
