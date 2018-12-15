package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public interface ZhiBanSubmitView<T> extends BaseView {
    void setList(T data);

    void Commitzhiban_View(T data);
    void list_Null();//刚进入界面为空时返回的回调函数
    void submit_View(T data);
}
