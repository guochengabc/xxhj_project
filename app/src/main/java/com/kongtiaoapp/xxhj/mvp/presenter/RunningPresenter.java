package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.HouseBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Runninglpl;
import com.kongtiaoapp.xxhj.mvp.view.RunningView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/5/18.
 */
public class RunningPresenter extends BasePresenterLpl<RunningView,Runninglpl> {
    @Override
    protected Runninglpl getModel() {
        return new Runninglpl();
    }
    public void onResume(Activity activity,boolean param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                HouseBean data= (HouseBean) response;
                if (data.getCode()==40000){
                    getView().setList(data);
                }else if (data.getCode()==40005){
                    getView().list_null();
                }
            }
        });
    }
}
