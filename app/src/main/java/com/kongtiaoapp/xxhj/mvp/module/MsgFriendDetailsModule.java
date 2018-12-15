package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface MsgFriendDetailsModule<T> extends BaseModule {
    void PingBi(Activity activity, T data, ResponseXXHJListener listener);

    void delete(Activity activity, T data, ResponseXXHJListener listener);

    void addFriend(Activity activity, T data, ResponseXXHJListener listener);

    void apply(Activity activity, T data, ResponseXXHJListener listener);
}
