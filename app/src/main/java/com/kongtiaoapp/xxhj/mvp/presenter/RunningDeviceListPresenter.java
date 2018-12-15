package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.ChangeTokenBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RunningDevice;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RunningDeviceListlpl;
import com.kongtiaoapp.xxhj.mvp.view.RunningDeviceListView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class RunningDeviceListPresenter extends BasePresenterLpl<RunningDeviceListView, RunningDeviceListlpl> {
    @Override
    protected RunningDeviceListlpl getModel() {
        return new RunningDeviceListlpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RunningDevice response = (RunningDevice) o;
                if (response.getCode() == 40000) {
                  getView().setText(response);
                } else if (response.getCode() == 40005) {

                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void changeToken(Activity activity, String param) {
        getModel().changeToken(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ChangeTokenBean response= (ChangeTokenBean) o;
                if (response.getCode()==40000){
                    getView().changeToken(response);
                }else{
                    ToastUtils.showToast(activity,response.getErrormsg());
                }
            }
        });
    }

    public void submit(Activity activity, String param) {

        getModel().submit_device(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response= (RBResponse) o;
                if (response.getCode() == 40000) {
                  getView().submit_View();
                } else {
                    ToastUtils.showToast(activity, "请检测参数是否填写正确");
                }
            }
        });
    }
}
