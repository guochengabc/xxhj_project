package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.WorkOrderRepairlpl;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderRepairView;

/**
 * Created by xxhj_g on 2017/6/24.   工单报修模块
 */

public class WorkOrderRepairPresenter extends BasePresenterLpl<WorkOrderRepairView,WorkOrderRepairlpl> {
    @Override
    protected WorkOrderRepairlpl getModel() {
        return new WorkOrderRepairlpl();
    }
    public void onResume(Activity activity){

    }
}
