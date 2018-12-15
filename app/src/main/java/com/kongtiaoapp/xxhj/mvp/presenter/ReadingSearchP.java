package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ReadingSearchl;
import com.kongtiaoapp.xxhj.mvp.view.ReadingSearchV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/11/22.
 */

public class ReadingSearchP extends BasePresenterLpl<ReadingSearchV,ReadingSearchl> {
    @Override
    protected ReadingSearchl getModel() {
        return new ReadingSearchl();
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
