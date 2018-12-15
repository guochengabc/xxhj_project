package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/5/30.  维保记录
 */

public interface WeiBaoRecordM<T> extends BaseModule {
    void delete(Activity activity, T data, ResponseXXHJListener listener);
}
