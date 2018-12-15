package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetSelectionInfo;
import com.kongtiaoapp.xxhj.bean.PostResult;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.SpecialDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.SpecialDetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class SpecialDetailPresenter extends BasePresenterLpl<SpecialDetailView,SpecialDetaillpl> {
    @Override
    protected SpecialDetaillpl getModel() {
        return new SpecialDetaillpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetSelectionInfo response = (GetSelectionInfo) o;
                if (response.getCode() == 40000) {
                    getView().setList(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
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
            public void requestSuccess(Object o) {
                PostResult result = (PostResult) o;
                if (result.getCode() == 40000) {
                    getView().comment_View(result);
                } else {
                    Logger.e(result.getResult());
                    ToastUtils.showToast(activity, result.getErrormsg());
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
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().deletePinglun_View(response);
                } else {
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

                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
