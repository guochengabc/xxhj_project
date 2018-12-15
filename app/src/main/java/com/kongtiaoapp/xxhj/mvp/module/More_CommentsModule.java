package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface More_CommentsModule<T> extends BaseModule {
    void commit_Comments(Activity activity, T data, ResponseXXHJListener listener);//提交留言

    void delete_Comments(Activity activity, T data, ResponseXXHJListener listener);//删除留言
}
