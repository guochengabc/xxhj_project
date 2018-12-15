package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RunningRecord_DutyBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RunningRecord_Dutylpl;
import com.kongtiaoapp.xxhj.mvp.view.RunningRecord_DutyView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/18.
 */
public class RunningRecord_DutyPresenter extends BasePresenterLpl<RunningRecord_DutyView, RunningRecord_Dutylpl> {
    @Override
    protected RunningRecord_Dutylpl getModel() {
        return new RunningRecord_Dutylpl();
    }

    public void onResume(Activity activity, List params) {
        getModel().getDataForservices(activity, params, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                RunningRecord_DutyBean data = (RunningRecord_DutyBean) response;
                if (data.getCode() == 40000) {
                    getView().setList(data);
                } else if (data.getCode() == 40005) {
                    getView().list_null();
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                    getView().error();
                }
            }
        });
    }
}
