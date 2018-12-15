package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.AlarmDetailBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AlarmDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.AlarmDetailViewq;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/9/25.
 */

public class AlarmDetailPresenter extends BasePresenterLpl<AlarmDetailViewq, AlarmDetaillpl> {
    @Override
    protected AlarmDetaillpl getModel() {
        return new AlarmDetaillpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                AlarmDetailBean bean = (AlarmDetailBean) o;
                AlarmDetailBean.ResobjBean resobj = bean.getResobj();
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(resobj);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
