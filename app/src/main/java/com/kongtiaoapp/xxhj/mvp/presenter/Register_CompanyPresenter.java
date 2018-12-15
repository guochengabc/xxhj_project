package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Register_Companylpl;
import com.kongtiaoapp.xxhj.mvp.view.Register_CompanyView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class Register_CompanyPresenter extends BasePresenterLpl<Register_CompanyView, Register_Companylpl> {
    @Override
    protected Register_Companylpl getModel() {
        return new Register_Companylpl();
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
