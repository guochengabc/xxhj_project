package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.WorkOrderListBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.WorkOrderlpl;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/6/23.
 */

public class WorkOrderPresenter extends BasePresenterLpl<WorkOrderView, WorkOrderlpl> {
    @Override
    protected WorkOrderlpl getModel() {
        return new WorkOrderlpl();
    }

    public void onResume(Activity activity, List<String> list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                WorkOrderListBean bean= (WorkOrderListBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setList(bean);
                }else if (bean.getCode()==EMPTY){
                    getView().setEmpty();
                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }
}
