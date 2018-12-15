package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface LoginModule<T> extends BaseModule {
    void getUserInfo(Activity activity, T data, ResponseXXHJListener listener);//获取用户信息
    void getProjectList(Activity activity, T data, ResponseXXHJListener listener);//获取项目列表
}
