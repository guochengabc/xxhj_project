package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/5/25.    暖通详情头部进入显示图表
 */

public interface ParamPaintM<T> extends BaseModule {
    void getPieChart(Activity activity, T data, ResponseXXHJListener listener);

    void getExplan(Activity activity, T data, ResponseXXHJListener listener);
}
