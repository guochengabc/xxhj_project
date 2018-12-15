package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceInfo;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.UserDeviceListDuolpl;
import com.kongtiaoapp.xxhj.mvp.view.UserDeviceListDuoView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class UserDeviceListDuoPresenter extends BasePresenterLpl<UserDeviceListDuoView, UserDeviceListDuolpl> {
    @Override
    protected UserDeviceListDuolpl getModel() {
        return new UserDeviceListDuolpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                DeviceInfo response = (DeviceInfo) o;
                if (response.getCode() == 40000) {
                    getView().setLsit(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
