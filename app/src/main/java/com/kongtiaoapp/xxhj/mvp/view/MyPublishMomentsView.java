package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public interface MyPublishMomentsView<T> extends BaseView {
    void setList(T data);

    void praise_View(T data);

    void favorite_View(T data);
}
