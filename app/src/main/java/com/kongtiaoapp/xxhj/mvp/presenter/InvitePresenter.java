package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.InviteCodeBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Invitelpl;
import com.kongtiaoapp.xxhj.mvp.view.InviteView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class InvitePresenter extends BasePresenterLpl<InviteView, Invitelpl> {
    @Override
    protected Invitelpl getModel() {
        return new Invitelpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                InviteCodeBean bean = (InviteCodeBean) o;
                if (bean.getCode() == 40000) {
                    getView().setText(bean);
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void resetInvieCode(Activity activity, String param) {
        getModel().resetInvateCode(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                InviteCodeBean bean = (InviteCodeBean) o;
                if (bean.getCode() == 40000) {
                    getView().resetInviteCode(bean);
                } else if (bean.getCode() == 40005) {
                    getView().setGone();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());

                }
            }
        });
    }

}
