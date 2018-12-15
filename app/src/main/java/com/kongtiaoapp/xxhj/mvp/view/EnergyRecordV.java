package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by G_XXHJ on 2018/6/27.  能源表单录入
 */

public interface EnergyRecordV<T> extends BaseView {
    void setList(T data);

    /**
     * 已废弃
     */
    void getRecordValue(T data);

    /**
     * 上一页
     */
    void getPre(T data);

    /**
     * 下一页
     */
    void getNext(T data);

    void getCommitData();
}
