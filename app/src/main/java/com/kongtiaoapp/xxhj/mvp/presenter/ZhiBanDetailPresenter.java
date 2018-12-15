package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.GetZhiBanInfo;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ZhiBanDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.ZhiBanDetailVeiw;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class ZhiBanDetailPresenter extends BasePresenterLpl<ZhiBanDetailVeiw, ZhiBanDetaillpl> {
    @Override
    protected ZhiBanDetaillpl getModel() {
        return new ZhiBanDetaillpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetZhiBanInfo response = (GetZhiBanInfo) o;
                if (response.getCode() == 40000) {
                    getView().setText(response);
                } else if (response.getCode() == 40005) {

                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void submit(Activity activity, List param) {
        getModel().submit(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().submit_View(response);
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void getLatePassWork(Activity activity, String param) {
        getModel().getLatePassWork(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetZhiBanInfo response = (GetZhiBanInfo) o;
                if (response.getCode() == 40000) {
                    getView().setText(response);
                } else if (response.getCode() == 40005) {
                    getView().setNull();
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void getLateTakeWork(Activity activity, String param) {
        getModel().getLatePassWork(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetZhiBanInfo response = (GetZhiBanInfo) o;
                if (response.getCode() == 40000) {
                    getView().setText(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, "您之前还没有进行接班哦！");
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
