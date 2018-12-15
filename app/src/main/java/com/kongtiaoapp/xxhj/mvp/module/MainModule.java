package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/4/25.
 */
public interface MainModule extends BaseModule {
    /**
     * 获取首页模块
     */
    void getModule(Activity activity, ResponseXXHJListener listener);
}
