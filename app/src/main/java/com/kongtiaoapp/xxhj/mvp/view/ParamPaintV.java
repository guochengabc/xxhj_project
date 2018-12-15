package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/5/25.
 */

public interface ParamPaintV<T> extends BaseView {
    /**
     * 获得饼状图
     */
    void getPieChart(T data);
    void gtPieChartNull();
    /**
     * 获取cop和scop解释和文本
     */
    void getExplan(T data);

    void list_null();
}
