package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.EnergyDeviceListBean;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceTitleBean;
import com.kongtiaoapp.xxhj.bean.EnergyMeterBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnergyRecordListl;
import com.kongtiaoapp.xxhj.mvp.view.EnergyRecordListV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by G_XXHJ on 2018/7/30.
 */

public class EnergyRecordListP extends BasePresenterLpl<EnergyRecordListV, EnergyRecordListl> {
    @Override
    protected EnergyRecordListl getModel() {
        return new EnergyRecordListl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyDeviceTitleBean bean = (EnergyDeviceTitleBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void getDeviceList(Activity activity, String param,String recordStatus,String status) {
        getModel().getDeviceList(activity, param,recordStatus,status, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyDeviceListBean bean = (EnergyDeviceListBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getDeviceList(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    getView().getError(bean.getErrormsg());
                }
            }
        });
    }

    public void getCheckBefore(Activity activity) {
        getModel().getCheckBefore(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                } else if (bean.getCode() == EMPTY) {
                } else {
                    getView().getError(bean.getErrormsg());
                }
            }
        });
    }
    public void getTopData(Activity activity) {
        getModel().getTopData(activity, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyMeterBean bean = (EnergyMeterBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getTop(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
