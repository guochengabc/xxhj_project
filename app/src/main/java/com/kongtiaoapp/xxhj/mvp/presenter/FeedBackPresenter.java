package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.FeedBacklpl;
import com.kongtiaoapp.xxhj.mvp.view.FeedBackView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class FeedBackPresenter extends BasePresenterLpl<FeedBackView, FeedBacklpl> {
    @Override
    protected FeedBacklpl getModel() {
        return new FeedBacklpl();
    }

    public void onResume(Activity activity, List list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(activity, "谢谢您的宝贵意见");
                    activity.finish();
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
