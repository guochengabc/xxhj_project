package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.getUserList;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.UserListlpl;
import com.kongtiaoapp.xxhj.mvp.view.UserListView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class UserListPresenter extends BasePresenterLpl<UserListView, UserListlpl> {
    @Override
    protected UserListlpl getModel() {
        return new UserListlpl();
    }

    public void onResume(Activity activity,String type) {
        getModel().getDataForservices(activity, type, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                getUserList info = (getUserList) response;
                if (info.getCode() == 40000) {
                    getView().setList(info);
                } else if (info.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, info.getErrormsg());
                }
            }
        });
    }
}
