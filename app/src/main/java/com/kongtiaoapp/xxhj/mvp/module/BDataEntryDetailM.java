package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/9/29.   变配电数据录入详情
 */

public interface BDataEntryDetailM<T> extends BaseModule {
    void saveDrag_CommitData(Activity activity, T data, ResponseXXHJListener listener);

    void commitData(Activity activity, T data, ResponseXXHJListener listener);

    void GetNameplateParaInfo(Activity activity, T data, ResponseXXHJListener listener);
}
