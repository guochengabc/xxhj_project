package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Register_Personallpl;
import com.kongtiaoapp.xxhj.mvp.view.Register_PersonalView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/18.
 */
public class Register_PersonalPresenter extends BasePresenterLpl<Register_PersonalView,Register_Personallpl> {
    @Override
    protected Register_Personallpl getModel() {
        return new Register_Personallpl();
    }

    public void getServiceData(Activity activity, List<String> param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                RBResponse data = (RBResponse) response;
                if (data.getCode() == 40000) {
                    getView().finishActivity();
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }

    public void sendIdentify(Activity activity, String param) {
        getModel().sendIdentify(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                RBResponse data = (RBResponse) response;
                if (data.getCode() == 40000) {
                    ToastUtils.showToast(activity, activity.getString(R.string.register_identify_succeed));
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }
}
