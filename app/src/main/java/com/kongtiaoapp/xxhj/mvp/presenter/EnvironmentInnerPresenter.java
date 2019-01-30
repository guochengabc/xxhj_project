package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnvironmentInnerlpl;
import com.kongtiaoapp.xxhj.mvp.view.EnvironmentInnerView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

public class EnvironmentInnerPresenter extends BasePresenterLpl<EnvironmentInnerView,EnvironmentInnerlpl> {
    @Override
    protected EnvironmentInnerlpl getModel() {
        return new EnvironmentInnerlpl();
    }
    public void getEnvironmentInfo(Activity activity){
        getModel().EnvironmentInnerInfo(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {

            }
        });
    }
    public void getEnvironmentPaint(Activity activity,String time){
        getModel().EnvironmentInnerPaint(activity, time, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {

            }
        });
    }
}
