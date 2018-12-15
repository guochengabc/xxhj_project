package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/6/21.  环境监测
 */

public interface EnviromentMonitoringV<T> extends BaseView {
    void lidt_null();
    void setList(T data);
}
