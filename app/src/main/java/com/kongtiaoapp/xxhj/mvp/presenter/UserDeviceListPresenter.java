package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceInfo;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.UserDeviceListlpl;
import com.kongtiaoapp.xxhj.mvp.view.UserDeviceListView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class UserDeviceListPresenter extends BasePresenterLpl<UserDeviceListView, UserDeviceListlpl> {
    @Override
    protected UserDeviceListlpl getModel() {
        return new UserDeviceListlpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                DeviceInfo dict = (DeviceInfo) response;
                if (dict.getCode() == 40000) {
                    getView().setList(dict);
                } else if (dict.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, dict.getErrormsg());
                }
            }
        });
    }
}
