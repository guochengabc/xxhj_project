package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/4/29.
 */

public interface InspectionView<T> extends BaseView {
    void setList(T data);
    void commitInsp(T data);
}
