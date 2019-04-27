package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * 新版暖通项目
 */
public interface HvacNewV<T> extends BaseView {
    /**
     * 获取
     *
     * @param data
     */
    void getMainData(T data);
    void getChart(T data);
}
