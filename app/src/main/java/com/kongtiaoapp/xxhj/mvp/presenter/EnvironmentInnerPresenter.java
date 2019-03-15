package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnvironmentInnerlpl;
import com.kongtiaoapp.xxhj.mvp.view.EnvironmentInnerView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

public class EnvironmentInnerPresenter extends BasePresenterLpl<EnvironmentInnerView, EnvironmentInnerlpl> {
    @Override
    protected EnvironmentInnerlpl getModel() {
        return new EnvironmentInnerlpl();
    }

    public void getEnvironmentInfo(Activity activity,String param) {
        getModel().EnvironmentInnerInfo(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnvironmentInnerBan bean = (EnvironmentInnerBan) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getEnvironmentInnerInfo(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {

                }
            }
        });
    }

    public void getEnvironmentPaint(Activity activity, List param) {
        getModel().EnvironmentInnerPaint(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnvironmentCPaintBean bean = (EnvironmentCPaintBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getEnvironmentInnerPaint(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().paintError(activity.getString(R.string.no_data));
                } else {
                    getView().paintError(bean.getErrormsg());
                }
            }
        });
    }
}
