package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ChangeShiftBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.view.ChangeShiftsView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/4.   交换班   电子签名
 */

public class ChangeShiftsPresenter extends BasePresenterLpl<ChangeShiftsView, ChangeShiftslpl> {
    @Override
    protected ChangeShiftslpl getModel() {
        return new ChangeShiftslpl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    ToastUtils.showToast(activity, activity.getString(R.string.commit_succeed));
                    activity.finish();
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

    public void CST(Activity activity) {
        getModel().getCST(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ChangeShiftBean bean = (ChangeShiftBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getCST(bean);
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.e_signtrue));
                } else {

                }
            }
        });
    }
}
