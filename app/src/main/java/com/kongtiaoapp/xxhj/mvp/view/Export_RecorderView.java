package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public interface Export_RecorderView<T> extends BaseView {
    void setList(T data);

    void list_null(String type);


    void getProjectName_View(T data);

    void getProjectModule_View(T data);

    void report_data_forservice_View(T data);

    void downDocument_View(T data);

    void succeed();

    void onfinish(T data);

    void onCancelled();

    void onError(String type);

    void onLoading(long total, long current, boolean isDownloading);

}
