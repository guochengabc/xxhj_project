package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.EnvironmentStateBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnviromentMonitoringl;
import com.kongtiaoapp.xxhj.mvp.view.EnviromentMonitoringV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by G_XXHJ on 2018/6/21.
 */

public class EnviromentMonitoringP extends BasePresenterLpl<EnviromentMonitoringV,EnviromentMonitoringl> {
    @Override
    protected EnviromentMonitoringl getModel() {
        return new EnviromentMonitoringl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnvironmentStateBean bean= (EnvironmentStateBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setList(bean);
                }else if (bean.getCode()==EMPTY){
                    getView().lidt_null();
                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }
}
