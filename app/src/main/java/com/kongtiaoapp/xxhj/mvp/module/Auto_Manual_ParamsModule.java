package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public interface Auto_Manual_ParamsModule<T> extends BaseModule {
    void setdataNextpage(Activity activity, T data, ResponseXXHJListener listener);//下一页
}
