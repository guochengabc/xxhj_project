package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/5/30.  维保详情
 */

public interface WeiBaoDetailM<T> extends BaseModule {
    /**
     * 维保修改
     */
    void modity(Activity activity, T data, ResponseXXHJListener listener);

    /**
     * 添加维保
     */
    void addWeiBao(Activity activity, T data, ResponseXXHJListener listener);
}
