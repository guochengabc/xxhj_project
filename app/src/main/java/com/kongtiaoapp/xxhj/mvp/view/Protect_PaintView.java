package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/9.
 */
public interface Protect_PaintView<T> extends BaseView {
    void paint_succeed_one();

    void paint_succeed_two();

    void paint_one(T data);

    void paint_two(T data);

    void paint_error_one();

    void paint_error_two();

    void paint_null_one();

    void paint_null_two();
}
