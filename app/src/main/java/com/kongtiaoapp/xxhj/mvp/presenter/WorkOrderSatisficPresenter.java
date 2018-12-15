package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.EngineerRecordFormBean;
import com.kongtiaoapp.xxhj.bean.RepairFormSecondBean;
import com.kongtiaoapp.xxhj.bean.WorkOrderStasficBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.WorkOrderSatisficlpl;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderSatisficView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/22.
 */

public class WorkOrderSatisficPresenter extends BasePresenterLpl<WorkOrderSatisficView, WorkOrderSatisficlpl> {
    @Override
    protected WorkOrderSatisficlpl getModel() {
        return new WorkOrderSatisficlpl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                WorkOrderStasficBean bean = (WorkOrderStasficBean) o;
                getView().getSucceedPaint("");
                if (bean.getCode() == SUCCEDD) {
                    getView().getStasticData(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().getListNull("");
                }
            }
        });
    }

    public void getEngineerRecordForm(Activity activity, List<String> list) {
        getModel().getEngineerRecordForm(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EngineerRecordFormBean bean = (EngineerRecordFormBean) o;
                getView().getSucceedPaint("");
                if (bean.getCode() == SUCCEDD) {
                    getView().engineerRecordForm(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().engineerRecordFormListNull();
                } else {
                    getView().engineerRecordFormError();
                }
            }
        });
    }
    public void getRepairRecordForm(Activity activity,List list){
        getModel().getRepairRecordForm(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RepairFormSecondBean bean= (RepairFormSecondBean) o;
                getView().getSecondSucceedPaint("");
                if (bean.getCode()==SUCCEDD){
                    getView().repairForm(bean);
                }else if (bean.getCode()==EMPTY){
                    getView().repairFormListNull();
                }else{
                    getView().repairFormError();
                }
            }
        });
    }
}
