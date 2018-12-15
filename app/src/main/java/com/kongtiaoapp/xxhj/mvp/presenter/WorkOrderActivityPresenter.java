package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RepairStatusBean;
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
    public void onCimmit(Activity activity, List<String> list,int size) {
        getModel().getCommit(activity, list,size, new ResponseXXHJListener() {
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

    public void getRepairStatus(Activity activity,int size, String param) {
        getModel().getCommit(activity, param,size, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RepairStatusBean bean = (RepairStatusBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getStatus(bean.getResobj().getDispState());
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
