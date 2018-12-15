package com.kongtiaoapp.xxhj.mvp.presenter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AddDeviceInfoActivity;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AddDevideModuleIpl;
import com.kongtiaoapp.xxhj.mvp.view.AddDeviceInfoView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class AddDevicePresenter extends BasePresenterLpl<AddDeviceInfoView, AddDevideModuleIpl> {
    @Override
    protected AddDevideModuleIpl getModel() {
        return new AddDevideModuleIpl();
    }

    public void ModifyDv(AddDeviceInfoActivity activity, String deviceId) {
        getModel().ModifyDv(activity, deviceId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                DeviceParam bean = (DeviceParam) o;
                if (bean.getCode() == 40000) {
                    getView().setList(bean);
                } else if (bean.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getResources().getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });

    }

    public void AddDv(AddDeviceInfoActivity activity, String type) {
        getModel().AddDv(activity, type, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                DeviceParam bean = (DeviceParam) o;
                if (bean.getCode() == 40000) {
                    getView().setList(bean);
                } else if (bean.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getResources().getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void SaveDv(AddDeviceInfoActivity activity, List list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(activity, activity.getString(R.string.save_success));
                    LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent().setAction(ReceiverAction.DEVICE_FRESH));
                    activity.finish();
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
