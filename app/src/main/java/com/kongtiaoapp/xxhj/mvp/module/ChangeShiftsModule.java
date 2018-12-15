package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/11/4.  交接班
 */

public interface ChangeShiftsModule<T> extends BaseModule {
    /**
     * 获取交接班类别
     */
    void getCST(Activity activity, T data, ResponseXXHJListener listener);//交接班类别
}
