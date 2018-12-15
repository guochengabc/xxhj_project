package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/11/4.  获取交接班类别
 */

public interface ChangeShiftsView<T> extends BaseView {
    void getCST(T data);
}
