package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Publishlpl;
import com.kongtiaoapp.xxhj.mvp.view.PublishView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class PublishPresenter extends BasePresenterLpl<PublishView,Publishlpl> {
    @Override
    protected Publishlpl getModel() {
        return new Publishlpl();
    }
    public void send(Activity activity,String param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response= (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().setText(response);
                } else {
                    ToastUtils.showToast(activity,response.getErrormsg());

                }
            }
        });
    }
}
