package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.HVAC_Mainlpl;
import com.kongtiaoapp.xxhj.mvp.view.HVAC_MainView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/6/20.
 */
public class HVAC_MainPresenter extends BasePresenterLpl<HVAC_MainView,HVAC_Mainlpl> {
    @Override
    protected HVAC_Mainlpl getModel() {
        return new HVAC_Mainlpl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {

            }
        });
    }
}
