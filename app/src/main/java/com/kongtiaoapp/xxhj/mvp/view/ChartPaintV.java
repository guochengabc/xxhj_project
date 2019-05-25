package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * 图表工具类View
 */
public interface ChartPaintV<T> extends BaseView {
    void getChartData(T data);
}
