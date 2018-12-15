package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ForgetPwdlpl;
import com.kongtiaoapp.xxhj.mvp.view.ForgetPwdView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class ForgetPwdPresenter extends BasePresenterLpl<ForgetPwdView, ForgetPwdlpl> {
    @Override
    protected ForgetPwdlpl getModel() {
        return new ForgetPwdlpl();
    }

    public void sendCode(Activity activity, String param) {
        getModel().sendCode(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(activity, "发送成功");
                } else {
                    ToastUtils.showToast(activity, "发送失败");
                }
            }
        });
    }

    public void changgePwd(Activity activity, List list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(activity, "修改成功");
                    activity.finish();
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
