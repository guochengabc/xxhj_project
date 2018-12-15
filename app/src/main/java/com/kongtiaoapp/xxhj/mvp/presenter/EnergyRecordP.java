package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnergyRecordBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnergyRecordl;
import com.kongtiaoapp.xxhj.mvp.view.EnergyRecordV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G_XXHJ on 2018/6/27.
 */

public class EnergyRecordP extends BasePresenterLpl<EnergyRecordV, EnergyRecordl> {
    @Override
    protected EnergyRecordl getModel() {
        return new EnergyRecordl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyRecordBean bean = (EnergyRecordBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setList(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    /**
     * 录值计算
     */
    public void caculatorData(Activity activity, List<String> list) {
        getModel().cacurlatorData(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyRecordBean bean = (EnergyRecordBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getRecordValue(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void commitData(Activity activity, List<EnergyRecordBean.ResobjBean.ElectricityBean> list, ArrayList<ImageItem> selImageList, String sensorId, String recordStatus) {
        getModel().commitData(activity, list, selImageList, sensorId, recordStatus, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    ToastUtils.showToast(activity, activity.getString(R.string.commit_succeed));
                    getView().getCommitData();
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void getPre(Activity activity, String sensorId) {
        getModel().getPre(activity, sensorId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyRecordBean bean = (EnergyRecordBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getPre(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity,bean.getErrormsg());
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void getNext(Activity activity, String sensorId) {
        getModel().getNext(activity, sensorId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyRecordBean bean = (EnergyRecordBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getNext(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity,bean.getErrormsg());
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
