package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface MyFriendsView<T> extends BaseView {
    void setList(T data);
}
