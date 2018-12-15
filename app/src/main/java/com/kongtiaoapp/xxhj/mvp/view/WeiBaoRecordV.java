package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/5/30.  维保记录
 */

public interface WeiBaoRecordV<T> extends BaseView {
    void getList(T data);
    void delete();
}
