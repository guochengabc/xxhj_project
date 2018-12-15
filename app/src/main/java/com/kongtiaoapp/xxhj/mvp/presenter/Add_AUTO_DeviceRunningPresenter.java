package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.Add_AUDO_DEVICE_PaintBean;
import com.kongtiaoapp.xxhj.bean.Add_AUTO_DeviceBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Add_AUTO_DeviceRunninglpl;
import com.kongtiaoapp.xxhj.mvp.view.Add_AUTO_DeviceRunningView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/7/11.
 */

public class Add_AUTO_DeviceRunningPresenter extends BasePresenterLpl<Add_AUTO_DeviceRunningView, Add_AUTO_DeviceRunninglpl> {
    @Override
    protected Add_AUTO_DeviceRunninglpl getModel() {
        return new Add_AUTO_DeviceRunninglpl();
    }

    public void onResumeDevice(Activity activity, List param) {
        getModel().onResumeDevice(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                Add_AUDO_DEVICE_PaintBean bean= (Add_AUDO_DEVICE_PaintBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setListPaint(bean);
                }else if (bean.getCode()==EMPTY){
                    getView().setList_nullPaint();
                }else{
                    getView().setList_error();
                }

            }
        });
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                List<RunningParam> response = (List<RunningParam>) o;
                if (response != null && response.size() > 0) {
                    getView().setList(response);
                } else {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                }
            }
        });
    }

    public void onResumeSystem(Activity activity, String param) {
        getModel().setDataForServiceSys(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                List<RunningParam> response = (List<RunningParam>) o;
                if (response != null && response.size() > 0) {
                    getView().setList(response);
                } else {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                }
            }
        });
    }

    public void saveDrag_CommitData(Activity activity, List list) {
        getModel().saveDrag_CommitData(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().saveDrag_CommitData_View(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void saveDrag_CommitData_Sys(Activity activity, List list) {
        getModel().saveDrag_CommitDataSys(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().saveDrag_CommitData_View(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void showPaint(Activity activity, List list) {
        getModel().showPaint(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                Add_AUTO_DeviceBean bean = (Add_AUTO_DeviceBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().showPaint(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    getView().errorlist();
                }
            }
        });
    }
}
