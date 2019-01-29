package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public interface MeModule<T> extends BaseModule {
    void quitLogin(Activity activity, T data, ResponseXXHJListener listener);
    void getAInfo(Activity activity,ResponseXXHJListener listener);
}
