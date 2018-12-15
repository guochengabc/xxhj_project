package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.WeiBaoDetailBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.WeiBaoDetaill;
import com.kongtiaoapp.xxhj.mvp.view.WeiBaoDetailV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/5/30.
 */

public class WeiBaoDetailP extends BasePresenterLpl<WeiBaoDetailV, WeiBaoDetaill> {
    @Override
    protected WeiBaoDetaill getModel() {
        return new WeiBaoDetaill();
    }

    public void onResume(Activity activity, String id) {
        getModel().getDataForservices(activity, id, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                WeiBaoDetailBean bean = (WeiBaoDetailBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(bean);
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void modify(Activity activity, List param) {
        getModel().modity(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    ToastUtils.showToast(activity, activity.getString(R.string.modify_succeed));
                    activity.finish();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void commitWeiBao(Activity activity, List param) {
        getModel().addWeiBao(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    ToastUtils.showToast(activity, activity.getString(R.string.add_succeed));
                    activity.finish();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
