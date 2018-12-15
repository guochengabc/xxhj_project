package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2017/12/14.
 */

public interface RunningGuideView<T> extends BaseView {
    void getList(T data);
    void list_null();
}
