package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/4/29.  巡检项
 */

public interface InspectionModule<T> extends BaseModule {
    /**提交巡检项*/
    void commitInsp(Activity activity, T data, ResponseXXHJListener listener);
}
