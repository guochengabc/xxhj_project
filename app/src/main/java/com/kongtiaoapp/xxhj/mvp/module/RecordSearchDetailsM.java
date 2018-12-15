package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * 录表查询详情
 */
public interface RecordSearchDetailsM<T,V,M,N> extends BaseModule {
    /**
     * 修改数据
     */
    void recallData(Activity activity, T data, ResponseXXHJListener listener);

    /**
     * 撤回数据
     */
    void modifyData(Activity activity, T dataO, V dataT,M dataTH,N dataF,ResponseXXHJListener listener);
}
