package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetPostList;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MyPublishTalkinglpl;
import com.kongtiaoapp.xxhj.mvp.view.MyPublishTalkingView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class MyPublishTalkingPresenter extends BasePresenterLpl<MyPublishTalkingView, MyPublishTalkinglpl> {
    @Override
    protected MyPublishTalkinglpl getModel() {
        return new MyPublishTalkinglpl();
    }
    public void onResume(Activity activity,String param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                GetPostList bean = (GetPostList) response;
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
