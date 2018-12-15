package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/6/23.
 */

public interface WorkOrderView<T> extends BaseView {
    void setList(T lists);

    void setEmpty();
}
