package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public interface ZhiBanDetailVeiw<T> extends BaseView{
    void submit_View(T data);
    void setNull();
}
