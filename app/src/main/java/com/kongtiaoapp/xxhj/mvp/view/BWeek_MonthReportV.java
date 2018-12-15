package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/9/14.  变配电  周报/月报
 */

public interface BWeek_MonthReportV<T> extends BaseView {
    void setList(T data);
    void listNull();
}
