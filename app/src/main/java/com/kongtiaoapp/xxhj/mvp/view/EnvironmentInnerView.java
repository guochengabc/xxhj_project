package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * 环境检测模块
 */
public interface EnvironmentInnerView<T> extends BaseView {
    /**
     * 获取环境监测首页
     */
    void getEnvironmentInnerInfo(T data);
    /**
     * 获取环境监测图表
     */
    void getEnvironmentInnerPaint(T data);
}
