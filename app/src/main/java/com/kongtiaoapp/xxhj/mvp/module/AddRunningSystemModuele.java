package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public interface AddRunningSystemModuele<T> extends BaseModule {
    void saveDrag_CommitData(Activity activity, T data, ResponseXXHJListener listener);
}