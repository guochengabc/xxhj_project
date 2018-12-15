package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/9/13.    变配电  报警阈值
 */

public interface BThresholdsV<T> extends BaseView {
    void setList(T data);

    void listNull();
}
