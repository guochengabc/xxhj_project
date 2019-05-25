package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.HVACPieChartBean;
import com.kongtiaoapp.xxhj.bean.ChartDataBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ParamPaintl;
import com.kongtiaoapp.xxhj.mvp.view.ParamPaintV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/5/25.
 */

public class ParamPaintP extends BasePresenterLpl<ParamPaintV, ParamPaintl> {
    @Override
    protected ParamPaintl getModel() {
        return new ParamPaintl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ChartDataBean bean = (ChartDataBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void onPieChart(Activity activity, List param) {
        getModel().getPieChart(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                HVACPieChartBean bean = (HVACPieChartBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getPieChart(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().gtPieChartNull();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void getExplan(Activity activity, List param) {
        getModel().getExplan(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ChartDataBean bean = (ChartDataBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
