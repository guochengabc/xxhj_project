package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public interface SpecialDetailView<T> extends BaseView {
    void comment_View(T data);

    void deletePinglun_View(T data);

    void favorite_View(T data);
    void setList(T data);
}
