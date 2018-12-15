package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface LoginView<T> extends BaseView {
    void getProjectList(T data);//获得用户账号和密码
}
