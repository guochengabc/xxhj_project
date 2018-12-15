package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MyGuidelpl;
import com.kongtiaoapp.xxhj.mvp.view.MyGuideView;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class MyGuidePresenter extends BasePresenterLpl<MyGuideView, MyGuidelpl> {
    @Override
    protected MyGuidelpl getModel() {
        return new MyGuidelpl();
    }
}
