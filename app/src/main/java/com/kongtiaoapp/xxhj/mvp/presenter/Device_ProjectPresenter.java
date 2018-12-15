package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Device_Projectlpl;
import com.kongtiaoapp.xxhj.mvp.view.Device_ProjectView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/9.
 */
public class Device_ProjectPresenter extends BasePresenterLpl<Device_ProjectView,Device_Projectlpl> {
    @Override
    protected Device_Projectlpl getModel() {
        return new Device_Projectlpl();
    }
    public void onResume(Activity activity,boolean dialog){
        getModel().getDataForservices(activity, dialog, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                DeviceInfoBean bean= (DeviceInfoBean) o;
                getView().setText("");
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
}
