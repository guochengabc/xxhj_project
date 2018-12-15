package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.MaterialBean;
import com.kongtiaoapp.xxhj.bean.OrderTimeBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Engineerlpl;
import com.kongtiaoapp.xxhj.mvp.view.EngineerView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public class EngineerPresenter extends BasePresenterLpl<EngineerView, Engineerlpl> {
    @Override
    protected Engineerlpl getModel() {
        return new Engineerlpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                WorkOrderGet bean = (WorkOrderGet) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setDetailInfo(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void onPros(Activity activity, List list, List<MaterialBean> listBean) {
        getModel().setPros(activity, list, listBean, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    ToastUtils.showToast(activity, activity.getString(R.string.commit_succeed));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void CommitForm(Activity activity, List<String> list, String isFinish) {
        getModel().Commit(activity, list, isFinish, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setCommit();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void commitEvaluate(Activity activity, List list) {
        getModel().evaluate(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setCommitEvaluate();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });

    }

    public void setOrderTaking(Activity activity, String dispatchId) {
        getModel().setOrderTaking(activity, dispatchId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                OrderTimeBean bean = (OrderTimeBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setOrderTaking(bean.getResobj().getOrderTime());
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
