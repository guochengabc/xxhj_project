package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.EnergyTopBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnergyMainlpl;
import com.kongtiaoapp.xxhj.mvp.view.EnergyMainV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

public class EnergyMainP extends BasePresenterLpl<EnergyMainV,EnergyMainlpl> {
    @Override
    protected EnergyMainlpl getModel() {
        return new EnergyMainlpl();
    }
    public void getMain(Activity activity,String projectId) {
        getModel().getDataForservices(activity, projectId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyTopBean bean= (EnergyTopBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setText(bean);
                }else if (bean.getCode()==EMPTY){

                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }
    public void getChart(Activity activity,List param) {
        getModel().getChart(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnvironmentCPaintBean bean= (EnvironmentCPaintBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().getChart(bean);
                }else if (bean.getCode()==EMPTY){

                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }
}
