package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/7/30. 能源计量设备列表录入
 */

public interface EnergyRecordListM<V,T,M> extends BaseModule {
    /**
     * 获得能源计量顶部数据
     */
    void getTopData(Activity activity, ResponseXXHJListener listener);
    /**
     * 获得能源计量三级结构的设备表  电水气
     */
    void getDeviceList(Activity activity, V data, T RecordStatus, M status, ResponseXXHJListener listener);
    /**
     * 录前检测  电水气
     */
    void getCheckBefore(Activity activity, V data, ResponseXXHJListener listener);
}
