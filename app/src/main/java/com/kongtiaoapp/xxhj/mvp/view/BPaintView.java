package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/10/9.  变配电图表
 */

public interface BPaintView<T> extends BaseView {
    void getTabView(T data);
    void list_null();
    void list_error(T data);
    void getFGDL(T data);
    void getFGDL_null();
    void getFGDL_error(T data);
}
