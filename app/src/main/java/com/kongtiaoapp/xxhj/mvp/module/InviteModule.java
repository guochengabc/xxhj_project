package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface InviteModule<T> extends BaseModule {
    void resetInvateCode(Activity activity, T data, ResponseXXHJListener listener);//重置邀请码
}
