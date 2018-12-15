package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.ProjectList;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ProjectInfolpl;
import com.kongtiaoapp.xxhj.mvp.view.ProjectInfoView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class ProjectInfoPresenter extends BasePresenterLpl<ProjectInfoView, ProjectInfolpl> {
    @Override
    protected ProjectInfolpl getModel() {
        return new ProjectInfolpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ProjectList response = (ProjectList) o;
                if (response.getCode() == 40000) {
                    getView().setText(response);
                } else if (response.getCode() == 40005) {
                    getView().list_null();
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void delete(Activity activity, String param) {
        getModel().delete(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().delete_View();
                } else if (response.getCode() == 40005) {

                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

}
