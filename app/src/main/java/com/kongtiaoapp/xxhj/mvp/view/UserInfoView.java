package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.bean.UserInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/7/5.
 */

public interface UserInfoView extends BaseView {
    void setList(UserInfoBean response);
    void RefreshAdapter();
}
