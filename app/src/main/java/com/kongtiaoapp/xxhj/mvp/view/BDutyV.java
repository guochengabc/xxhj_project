package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/9/13.   变配电  值班管理
 */

public interface BDutyV<T> extends BaseView {
    void  setList(T data);
    void list_null();
}
