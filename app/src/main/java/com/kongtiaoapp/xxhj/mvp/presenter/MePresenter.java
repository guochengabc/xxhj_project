package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.UserInfo;
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Melpl;
import com.kongtiaoapp.xxhj.mvp.view.MeView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class MePresenter extends BasePresenterLpl<MeView, Melpl> {
    @Override
    protected Melpl getModel() {
        return new Melpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                UserInfo data = (UserInfo) response;
                if (data.getCode() == 40000) {
                    getView().setText(data);
                } else if (data.getCode() == 40005) {

                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }

    /*退出登录*/
    public void Quit_Login(Activity activity) {
        getModel().quitLogin(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse data = (RBResponse) o;
                if (data.getCode() == 40000) {
                    App.sp.setLoginState(false);
                    App.sp.resetSp();
                    System.gc();
                    AllActivityManager.getInstance().finishAllActivity();
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    activity.finish();
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }
}
