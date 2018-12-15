package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public interface MsgFriendDetailsView<T> extends BaseView {
    void PingBi_View(T data);

    void delete_View(T data);

    void addFriend_View(T data);

    void apply_View(T data);

}
