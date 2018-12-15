package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.Loading_RefrigeratorBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Protect_Paintlpl;
import com.kongtiaoapp.xxhj.mvp.view.Protect_PaintView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/9.
 */
public class Protect_PaintPresenter extends BasePresenterLpl<Protect_PaintView, Protect_Paintlpl> {
    @Override
    protected Protect_Paintlpl getModel() {
        return new Protect_Paintlpl();
    }

    public void paint_one(Activity activity, List<String> list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                Loading_RefrigeratorBean bean = (Loading_RefrigeratorBean) o;
                getView().paint_succeed_one();
                if (bean.getCode() == 40000) {
                    getView().paint_one(bean);
                } else if (bean.getCode() == 40005) {
                    getView().paint_null_one();
                } else {
                    getView().paint_error_one();
                }
            }
        });
    }

    public void paint_two(Activity activity, List<String> list) {
        getModel().getDataForServers_two(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                Loading_RefrigeratorBean bean = (Loading_RefrigeratorBean) o;
                getView().paint_succeed_two();
                if (bean.getCode() == 40000) {
                    getView().paint_two(bean);
                } else if (bean.getCode() == 40005) {
                    getView().paint_null_two();
                } else {
                    getView().paint_error_two();
                }
            }
        });
    }
}
