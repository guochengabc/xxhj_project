package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * 获取能源管理UBP各变电站
 */
public interface EnergyMainV<T> extends BaseView {
    void getChart(T data);
}
