package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.BWeekReportDetailBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BWeekReportDetaill;
import com.kongtiaoapp.xxhj.mvp.view.BWeekReportDetailV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/9/15.
 */

public class BWeekReportDetailP extends BasePresenterLpl<BWeekReportDetailV, BWeekReportDetaill> {
    @Override
    protected BWeekReportDetaill getModel() {
        return new BWeekReportDetaill();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                BWeekReportDetailBean bean = (BWeekReportDetailBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
