package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.AlarmListBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Alarmlpl;
import com.kongtiaoapp.xxhj.mvp.view.AlarmView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/6/22.
 */

public class AlarmPresenter extends BasePresenterLpl<AlarmView, Alarmlpl> {
    @Override
    protected Alarmlpl getModel() {
        return new Alarmlpl();
    }

    public void onResume(Activity activity, List<String> param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                AlarmListBean bean = (AlarmListBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setList(bean);
                } else if (bean.getCode() == EMPTY) {
                   getView().listNull();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
