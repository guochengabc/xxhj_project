package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public interface EnergyShowDetailModule<T> extends BaseModule {
    void favorate(Activity activity, T data, ResponseXXHJListener listener);
}
