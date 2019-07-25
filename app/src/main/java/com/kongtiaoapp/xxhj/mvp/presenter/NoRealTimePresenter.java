package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.bean.NoRealTimeBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.NoRealTimelpl;
import com.kongtiaoapp.xxhj.mvp.view.NoRealTimeView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

public class NoRealTimePresenter extends BasePresenterLpl<NoRealTimeView, NoRealTimelpl> {
    @Override
    protected NoRealTimelpl getModel() {
        return new NoRealTimelpl();
    }

    public void onResume(Activity activity, String projectId) {
        getModel().getDataForservices(activity, projectId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnvironmentInnerBan bean = (EnvironmentInnerBan) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getMainData(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    /**
     *   日图表
     * @param activity
     * @param param
     */
    public void getChart(Activity activity, List param) {
        getModel().getChart(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                NoRealTimeBean bean = (NoRealTimeBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getChart(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    /**
     *   月图表
     * @param activity
     * @param param
     */
    public void getChartMonth(Activity activity, List param) {
        getModel().getChartMonth(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnvironmentCPaintBean bean = (EnvironmentCPaintBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getChartMonth(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

}
