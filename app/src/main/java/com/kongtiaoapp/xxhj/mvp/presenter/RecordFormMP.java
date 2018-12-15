package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RecordFormMBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RecordFormMl;
import com.kongtiaoapp.xxhj.mvp.view.RecordFormMV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by G_XXHJ on 2018/11/22.
 */

public class RecordFormMP extends BasePresenterLpl<RecordFormMV, RecordFormMl> {
    @Override
    protected RecordFormMl getModel() {
        return new RecordFormMl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RecordFormMBean bean = (RecordFormMBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getList(bean);
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
