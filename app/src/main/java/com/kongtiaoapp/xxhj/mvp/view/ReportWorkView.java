package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.bean.Report_Running_RecordBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/8/26.
 */

public interface ReportWorkView<T> extends BaseView {
        void getDataSuccedd(Report_Running_RecordBean bean);
        void onLoading(long total, long current, boolean isDownloading);
        void succeed();

        void onfinish(T data);

        void onCancelled();

        void onError(String type);

}
