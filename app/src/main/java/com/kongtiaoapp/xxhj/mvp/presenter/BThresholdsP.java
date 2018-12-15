package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.ParamSettingBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BThresholdsl;
import com.kongtiaoapp.xxhj.mvp.view.BThresholdsV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/9/13.  变配电  报警阈值
 */

public class BThresholdsP extends BasePresenterLpl<BThresholdsV,BThresholdsl> {
    @Override
    protected BThresholdsl getModel() {
        return new BThresholdsl();
    }
    public void onResume(Activity activity,String param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ParamSettingBean bean= (ParamSettingBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setList(bean);
                }else if (bean.getCode()==EMPTY){
                    getView().listNull();
                }else{

                }
            }
        });
    }
}
