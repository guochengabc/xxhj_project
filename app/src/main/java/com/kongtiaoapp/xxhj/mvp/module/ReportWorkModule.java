package com.kongtiaoapp.xxhj.mvp.module;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;

import org.xutils.common.Callback;

import java.io.File;

/**
 * Created by xxhj_g on 2017/8/26.
 */

public interface ReportWorkModule<T> extends BaseModule {
    void downDocument(T data, Callback.ProgressCallback<File> listener);//下载文件
}
