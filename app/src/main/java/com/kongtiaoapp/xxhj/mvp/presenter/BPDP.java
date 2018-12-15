package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.BPD_MainInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BPDl;
import com.kongtiaoapp.xxhj.mvp.view.BPDV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by G_XXHJ on 2018/9/7.  变配电
 */

public class BPDP extends BasePresenterLpl<BPDV,BPDl> {
    @Override
    protected BPDl getModel() {
        return new BPDl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                BPD_MainInfoBean bean = (BPD_MainInfoBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
    }

