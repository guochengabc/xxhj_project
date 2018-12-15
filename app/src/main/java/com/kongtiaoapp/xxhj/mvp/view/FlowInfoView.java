package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/8/10.
 */

public interface FlowInfoView<T> extends BaseView {
    void setList(T data);
}
