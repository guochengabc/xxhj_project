package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.WeibaoBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AddWBRecordlpl;
import com.kongtiaoapp.xxhj.mvp.view.AddWBRecordView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class AddWBRecordPresenter extends BasePresenterLpl<AddWBRecordView, AddWBRecordlpl> {
    @Override
    protected AddWBRecordlpl getModel() {
        return new AddWBRecordlpl();
    }

    public void onResume(Activity activity, String maintenanceRecordId) {
        getModel().getDataForservices(activity, maintenanceRecordId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                WeibaoBean bean = (WeibaoBean) o;
                if (bean.getCode() == 40000) {
                    getView().setText(bean);
                } else if (bean.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });

    }

    public void save(Activity activity, List<String> list) {
        getModel().save(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                WeibaoBean bean = (WeibaoBean) o;
                if (bean.getCode() == 40000) {
                    ToastUtils.showToast(activity,activity.getString(R.string.weibao_add_succeed));
                    LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent().setAction(ReceiverAction.WEIBAO_FRESH));
                    activity.finish();
                } else if (bean.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
