package com.kongtiaoapp.xxhj.net.okhttp;

/**
 * Created by Administrator on 2016/11/4.
 */
public interface ResponseXXHJListener<T> {
    void requuestError(int code);

    void requestSuccess(T t);
}
