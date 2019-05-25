package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Faniciallpl;
import com.kongtiaoapp.xxhj.mvp.view.HvacNewV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

public class FanicialPresenter extends BasePresenterLpl<HvacNewV,Faniciallpl> {
    @Override
    protected Faniciallpl getModel() {
        return new Faniciallpl();
    }
    public void onResume(Activity activity,String projectId){
        getModel().getDataForservices(activity, projectId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnvironmentInnerBan bean= (EnvironmentInnerBan) o;
                if (bean.getCode()==SUCCEDD){
                    getView().getMainData(bean);
                }else if (bean.getCode()==EMPTY){

                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    } public void getChart(Activity activity, List param){
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
