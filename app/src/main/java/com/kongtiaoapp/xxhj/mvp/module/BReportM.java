package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import org.xutils.common.Callback;

import java.io.File;

/**
 * Created by G_XXHJ on 2018/9/13.  变配电  报表导出
 */

public interface BReportM<T> extends BaseModule {
    void getProjectName(Activity activity, T data, ResponseXXHJListener listener);//项目名称

    void getProjectModule(Activity activity, T data, ResponseXXHJListener listener);//项目模板

    void report_data_forservice(Activity activity, T data, ResponseXXHJListener listener);//获取url下载文件

    void downDocument(T data, Callback.ProgressCallback<File> listener);//下载文件
}
