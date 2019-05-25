package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * 生成图表Module
 */
public interface ChartPaintM extends BaseModule {
    void requestData(Activity activity,String dateStr,String type, ResponseXXHJListener listener);
}
