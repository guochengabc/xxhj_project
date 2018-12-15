package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.PublishPostlpl;
import com.kongtiaoapp.xxhj.mvp.view.PublishPostView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class PublishPostPresenter extends BasePresenterLpl<PublishPostView, PublishPostlpl> {
    @Override
    protected PublishPostlpl getModel() {
        return new PublishPostlpl();
    }

    public void onResume(Activity activity, List param) {
        getModel().setUploadPictrue(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().setText(response);
                } else {
                    ToastUtils.showToast(activity, "发布失败");
                }
            }
        });
    }

}
