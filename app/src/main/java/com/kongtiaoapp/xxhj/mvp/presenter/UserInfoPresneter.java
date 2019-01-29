package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.UserInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.UserInfolpl;
import com.kongtiaoapp.xxhj.mvp.view.UserInfoView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/7/5.
 */

public class UserInfoPresneter extends BasePresenterLpl<UserInfoView, UserInfolpl> {
    @Override
    protected UserInfolpl getModel() {
        return new UserInfolpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                UserInfoBean userInfoBean = (UserInfoBean) o;
                if (userInfoBean.getCode() == SUCCEDD) {
                    getView().setList(userInfoBean);
                } else if (userInfoBean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, userInfoBean.getErrormsg());
                }
            }
        });
    }

    public void ModifyManager(Activity activity, List<String> list) {
        getModel().getModifyManager(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == SUCCEDD) {
                    getView().RefreshAdapter();
                    ToastUtils.showToast(activity, activity.getString(R.string.modify_manager));
                } else if (response.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void deletePhone(Activity activity, List<Map<String, String>> list) {
        getModel().deleteNumber(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == SUCCEDD) {
                    getView().RefreshAdapter();
                    ToastUtils.showToast(activity, activity.getString(R.string.deleteNum));
                } else if (response.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
