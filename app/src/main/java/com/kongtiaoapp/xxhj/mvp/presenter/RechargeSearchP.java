package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RechargeSearchl;
import com.kongtiaoapp.xxhj.mvp.view.RechargeSearchV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/11/22.
 */

public class RechargeSearchP extends BasePresenterLpl<RechargeSearchV,RechargeSearchl> {
    @Override
    protected RechargeSearchl getModel() {
        return new RechargeSearchl();
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
