package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/6/27.      能源表单录入
 */

public interface EnergyRecordM<T, M, V, N> extends BaseModule {
    void cacurlatorData(Activity activity, T data, ResponseXXHJListener listener);

    void commitData(Activity activity, T data, M data1, V data2, N data3, ResponseXXHJListener listener);

    /**
     * 获取上一页数据
     */
    void getPre(Activity activity, T data, ResponseXXHJListener listener);

    /**
     * 获取下一页数据
     */
    void getNext(Activity activity, T data, ResponseXXHJListener listener);
}
