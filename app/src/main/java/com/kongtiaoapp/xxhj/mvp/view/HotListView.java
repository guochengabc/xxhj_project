package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface HotListView<T> extends BaseView {
    void setList(T data);
    void praise_view(T data);
    void favorite_view(T data);
}
