package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MReadingl;
import com.kongtiaoapp.xxhj.mvp.view.MReadingV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/11/22.   模块抄表
 */

public class MReadingP extends BasePresenterLpl<MReadingV,MReadingl> {
    @Override
    protected MReadingl getModel() {
        return new MReadingl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {

            }
        });
    }
}
