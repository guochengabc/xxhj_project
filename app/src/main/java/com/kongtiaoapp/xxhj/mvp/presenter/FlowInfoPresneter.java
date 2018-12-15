package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.FlowInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.FlowInfolpl;
import com.kongtiaoapp.xxhj.mvp.view.FlowInfoView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/8/10.
 */

public class FlowInfoPresneter extends BasePresenterLpl<FlowInfoView, FlowInfolpl> {
    @Override
    protected FlowInfolpl getModel() {
        return new FlowInfolpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                FlowInfoBean bean = (FlowInfoBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setList(bean.getResobj());
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
