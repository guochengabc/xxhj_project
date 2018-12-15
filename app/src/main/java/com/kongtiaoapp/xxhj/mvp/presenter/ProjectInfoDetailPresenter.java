package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.getProjectInfo;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ProjectInfoDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.ProjectInfoDetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class ProjectInfoDetailPresenter extends BasePresenterLpl<ProjectInfoDetailView, ProjectInfoDetaillpl> {
    @Override
    protected ProjectInfoDetaillpl getModel() {
        return new ProjectInfoDetaillpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                getProjectInfo response = (getProjectInfo) o;
                if (response.getCode() == 40000) {
                   getView().setText(response);
                } else {
                    ToastUtils.showToast(activity,response.getErrormsg());
                }
            }
        });

    }

    public void submit(Activity activity, String param) {
        getModel().submit(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response= (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().submit_View();
                } else {
                   ToastUtils.showToast(activity,response.getErrormsg());
                }
            }
        });

    }

}
