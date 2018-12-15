package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public interface WeiBaoRecordView<T> extends BaseView {
    void setList(T data);

    void delete_View();

    void list_Null();//响应吗为40005时走的回调
}
