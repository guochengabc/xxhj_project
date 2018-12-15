package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface More_CommentsView<T> extends BaseView {
    void delete_Comments_View(T data);

    void commit_Comments_View(T data);

    void notifyAdapter();
}
