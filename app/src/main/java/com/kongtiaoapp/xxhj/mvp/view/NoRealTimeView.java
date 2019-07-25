package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * 不是具体某个时间段有数据
 */
public interface NoRealTimeView<T> extends BaseView {
    /**
     * 获取
     *
     * @param data
     */
    void getMainData(T data);
    void getChart(T data);
    void getChartMonth(T data);

}
