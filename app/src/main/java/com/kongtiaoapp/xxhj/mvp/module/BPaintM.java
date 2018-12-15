package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/10/9.  变配电图表
 */

public interface BPaintM<T> extends BaseModule {
    void getTabView(Activity activity, T data, ResponseXXHJListener listener);
}
