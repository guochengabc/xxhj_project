package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/4/28.
 */
public interface MainView<T> extends BaseView{
    void VersionCheck(T check);
    void getModule(T data);
}
