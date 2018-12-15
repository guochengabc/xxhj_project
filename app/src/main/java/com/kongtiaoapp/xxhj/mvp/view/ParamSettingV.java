package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/6/8.   参数设置
 */

public interface ParamSettingV<T> extends BaseView {
    void setList(T data);

    void listNull();
}
