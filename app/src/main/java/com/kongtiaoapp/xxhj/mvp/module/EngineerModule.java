package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public interface EngineerModule<T> extends BaseModule {
    void setOrderTaking(Activity activity, String dis, ResponseXXHJListener listener);
    void setPros(Activity activity, T data, T list, ResponseXXHJListener listener);//勘察上报
    void Commit(Activity activity, T data, String isFinish, ResponseXXHJListener listener);//提交报单
    void evaluate(Activity activity, T data, ResponseXXHJListener listener);//评价
}
