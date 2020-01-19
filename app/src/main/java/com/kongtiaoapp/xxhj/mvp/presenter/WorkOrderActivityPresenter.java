package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.LocationAllBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.SystemBean;
import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.WorkOrderActivitylpl;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderActivityView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public class WorkOrderActivityPresenter extends BasePresenterLpl<WorkOrderActivityView, WorkOrderActivitylpl> {
    @Override
    protected WorkOrderActivitylpl getModel() {
        return new WorkOrderActivitylpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                WorkOrderGet bean = (WorkOrderGet) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setDetailInfo(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    //调度员提交派工单
    public void onCimmit(Activity activity, List<String> list,List<String>listSys,  int size) {
        getModel().getCommit(activity, list,listSys,size, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse rbResponse = (RBResponse) o;
                if (rbResponse.getCode() == SUCCEDD) {

                    getView().setCommit();
                } else {
                    ToastUtils.showToast(activity, rbResponse.getErrormsg());
                }
            }
        });
    }


    public void getSystem(Activity activity) {
        getModel().getSystem(activity, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                SystemBean bean= (SystemBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().getSystem(bean.getResobj());
                }else{
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void getLocation(Activity activity, String whichType) {
        getModel().getLocation(activity, whichType, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                LocationAllBean bean= (LocationAllBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().getLocation(bean.getResobj());
                }else{
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
