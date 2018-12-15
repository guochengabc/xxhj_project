package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2017/12/22.热源计量
 */

public interface HeatMeteringView<T> extends BaseView {
    void getTopList(T data);
}
