package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RunDevice_StateBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Running_Projectlpl;
import com.kongtiaoapp.xxhj.mvp.view.Running_ProjectView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/18.
 */
public class Running_ProjectPresenter extends BasePresenterLpl<Running_ProjectView, Running_Projectlpl> {
    @Override
    protected Running_Projectlpl getModel() {
        return new Running_Projectlpl();
    }

    public void onResume(Activity activity, boolean param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                RunDevice_StateBean data = (RunDevice_StateBean) response;
                getView().setText("");
                if (data.getCode() == 40000) {
                    getView().setList(data);
                } else if (data.getCode() == 40005) {
                    getView().setText("no_data");
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }
}
