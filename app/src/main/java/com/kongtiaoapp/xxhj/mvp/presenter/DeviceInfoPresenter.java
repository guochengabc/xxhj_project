package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.DictNew;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.DeviceInfolpl;
import com.kongtiaoapp.xxhj.mvp.view.DeviceInfoView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class DeviceInfoPresenter extends BasePresenterLpl<DeviceInfoView,DeviceInfolpl> {
    @Override
    protected DeviceInfolpl getModel() {
        return new DeviceInfolpl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                DictNew response= (DictNew) o;
                if (response.getCode()==40000){
                    getView().setList(response);
                } else if (response.getCode()==40005){

                }else{
                    ToastUtils.showToast(activity,response.getErrormsg());
                }
            }
        });
    }
}
