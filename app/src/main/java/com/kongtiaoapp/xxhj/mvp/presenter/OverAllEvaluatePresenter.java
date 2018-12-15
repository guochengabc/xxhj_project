package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.OverRunEvaluateBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.OverAllEvaluatelpl;
import com.kongtiaoapp.xxhj.mvp.view.OverAllEvaluateView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/7/17.
 */

public class OverAllEvaluatePresenter extends BasePresenterLpl<OverAllEvaluateView, OverAllEvaluatelpl> {
    @Override
    protected OverAllEvaluatelpl getModel() {
        return new OverAllEvaluatelpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                OverRunEvaluateBean bean = (OverRunEvaluateBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setList(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
