package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public interface PostDetailView<T> extends BaseView {
    void getPinglun(T data);

    void comment_View(T data);

    void deleteMoments_View(T data);

    void deletePinglun_View(T data);

    void favoritet_View();

    void praise_View();

}
