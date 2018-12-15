package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/7/30.  能源计量设备列表
 */

public interface EnergyRecordListV<T> extends BaseView {
    /**
     * 顶部数据
     */
    void getTop(T data);

    void getDeviceList(T data);

    void list_null();

    void getError(T data);
}
