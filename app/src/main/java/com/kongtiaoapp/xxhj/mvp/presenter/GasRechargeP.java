package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.GasRechargel;
import com.kongtiaoapp.xxhj.mvp.view.GasRechargeV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/11/22.  燃气充值
 */

public class GasRechargeP extends BasePresenterLpl<GasRechargeV,GasRechargel> {
    @Override
    protected GasRechargel getModel() {
        return new GasRechargel();
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
