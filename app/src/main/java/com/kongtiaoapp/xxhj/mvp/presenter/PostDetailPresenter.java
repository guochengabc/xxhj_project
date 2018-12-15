package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.bean.PostPinglun;
import com.kongtiaoapp.xxhj.bean.PostResult;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.PostDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.PostDetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class PostDetailPresenter extends BasePresenterLpl<PostDetailView, PostDetaillpl> {
    @Override
    protected PostDetaillpl getModel() {
        return new PostDetaillpl();
    }

    public void getPinglun(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                PostPinglun response = (PostPinglun) o;
                if (response.getCode() == 40000) {
                    getView().getPinglun(response);
                } else if (response.getCode() == 40005) {
                    getView().setText("getNotify");
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void comment(Activity activity, List param) {
        getModel().comment(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                PostResult result = (PostResult) response;
                if (result.getCode() == 40000) {
                    getView().comment_View(result);
                } else {
                    Logger.e(result.getResult());
                    ToastUtils.showToast(activity, result.getErrormsg());
                }
            }
        });
    }

    public void deleteMoments(Activity activity, String param) {
        getModel().deleteMoments(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object data) {
                PostResult response = (PostResult) data;
                if (response.getCode() == 40000) {
                    getView().deleteMoments_View(response);
                } else if (response.getCode() == 40005) {
                } else {
                    Logger.e(response.getResult());
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void deletePinglun(Activity activity, String param) {
        getModel().deletePinglun(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                PostResult response = (PostResult) o;
                if (response.getCode() == 40000) {
                    App.sp.setRefresh("yes");
                    ToastUtils.showToast(activity, "删除成功");
                    getView().deletePinglun_View(response);
                } else if (response.getCode() == 40005) {
                } else {
                    Logger.e(response.getResult());
                    ToastUtils.showToast(activity, response.getErrormsg());

                }
            }
        });
    }

    public void favorite(Activity activity, List param) {
        getModel().favorite(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().favoritet_View();
                } else if (response.getCode() == 40005) {

                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void praise(Activity activity, List list) {
        getModel().praise(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().praise_View();
                } else if (response.getCode() == 40005) {
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

}
