package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.SystemParam;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AddRunningSystemlpl;
import com.kongtiaoapp.xxhj.mvp.view.AddRunningSystemView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;


/**
 * Created by xxhj_g on 2017/5/6.
 */
public class AddRunningSystemPresenter extends BasePresenterLpl<AddRunningSystemView,AddRunningSystemlpl> {
    @Override
    protected AddRunningSystemlpl getModel() {
        return new AddRunningSystemlpl();
    }
    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                SystemParam response = (SystemParam) o;
                if (response.getCode() == 40000) {
                    getView().setList(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void saveDrag_CommitData(Activity activity, String param) {
        getModel().saveDrag_CommitData(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().saveDrag_CommitData_View(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
