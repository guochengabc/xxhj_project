package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class MyStainPresenter extends BasePresenterLpl {
    @Override
    protected BaseModule getModel() {
        return new BaseModule() {
            @Override
            public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

            }
        };
    }
}
