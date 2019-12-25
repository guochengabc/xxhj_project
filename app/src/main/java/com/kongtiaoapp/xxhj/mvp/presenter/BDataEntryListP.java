package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.BPD_DataEntryBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BDataEntryListl;
import com.kongtiaoapp.xxhj.mvp.view.BDataEntryListV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by G_XXHJ on 2018/9/14.   变配电  数据录入列表
 */

public class BDataEntryListP extends BasePresenterLpl<BDataEntryListV, BDataEntryListl> {
    @Override
    protected BDataEntryListl getModel() {
        return new BDataEntryListl();
    }

    public void onResume(Activity activity,String projectId) {
        getModel().getDataForservices(activity, projectId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                BPD_DataEntryBean bean = (BPD_DataEntryBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setList(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
