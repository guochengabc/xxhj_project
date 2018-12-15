package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/7/17.长按识别图中二维码
 */

public interface LongPressV<T> extends BaseView {
    void setList(T data);
}
