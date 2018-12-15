package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.bean.DutyInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Duty_Projectlpl;
import com.kongtiaoapp.xxhj.mvp.view.Duty_ProjectView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/9.
 */
public class Duty_ProjectPresenter extends BasePresenterLpl<Duty_ProjectView, Duty_Projectlpl> {
    @Override
    protected Duty_Projectlpl getModel() {
        return new Duty_Projectlpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                DutyInfoBean bean = (DutyInfoBean) response;
                App.sp.removeSp("uName");
                App.sp.removeSp("cPhone");
                if (bean.getCode() == 40000) {
                    getView().setList(bean);
                } else if (bean.getCode() == 40005) {
                    getView().list_null();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
