package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MyPublishlpl;
import com.kongtiaoapp.xxhj.mvp.view.MyPublishView;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class MyPublishPresenter extends BasePresenterLpl<MyPublishView,MyPublishlpl> {

    @Override
    protected MyPublishlpl getModel() {
        return new MyPublishlpl();
    }
}
