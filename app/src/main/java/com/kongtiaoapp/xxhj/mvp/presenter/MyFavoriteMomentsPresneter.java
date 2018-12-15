package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.Moments;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MyFavoriteMomentslpl;
import com.kongtiaoapp.xxhj.mvp.view.MyFavoriteMomentsView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class MyFavoriteMomentsPresneter extends BasePresenterLpl<MyFavoriteMomentsView, MyFavoriteMomentslpl> {
    @Override
    protected MyFavoriteMomentslpl getModel() {
        return new MyFavoriteMomentslpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                Moments data = (Moments) response;
                if (data.getCode() == 40000) {
                    getView().setList(data);
                } else if (data.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }

    public void praise(Activity activity, List param) {
        getModel().praise(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                RBResponse data = (RBResponse) response;
                if (data.getCode() == 40000) {
                    getView().praise_View(data);
                } else if (data.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
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
            public void requestSuccess(Object response) {
                RBResponse data = (RBResponse) response;
                if (data.getCode() == 40000) {
                    getView().favorite_View(data);
                } else if (data.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }
}
