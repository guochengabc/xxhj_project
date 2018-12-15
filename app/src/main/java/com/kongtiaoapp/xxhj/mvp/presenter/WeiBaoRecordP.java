package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.MaintBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.WeiBaoRecordl;
import com.kongtiaoapp.xxhj.mvp.view.WeiBaoRecordV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/5/30.
 */

public class WeiBaoRecordP extends BasePresenterLpl<WeiBaoRecordV, WeiBaoRecordl> {
    @Override
    protected WeiBaoRecordl getModel() {
        return new WeiBaoRecordl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                MaintBean bean = (MaintBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getList(bean.getResobj());
                } else if (bean.getCode() == EMPTY) {
                } else {

                }
            }
        });
    }

    public void delete(Activity activity, String param) {
        getModel().delete(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().delete();
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
