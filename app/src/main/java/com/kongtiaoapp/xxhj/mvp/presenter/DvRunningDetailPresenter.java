package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.DvRunningDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.DvRunningDetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class DvRunningDetailPresenter extends BasePresenterLpl<DvRunningDetailView, DvRunningDetaillpl> {
    @Override
    protected DvRunningDetaillpl getModel() {
        return new DvRunningDetaillpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                DeviceParam response = (DeviceParam) o;
                if (response.getCode() == 40000) {
                    getView().setList(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
