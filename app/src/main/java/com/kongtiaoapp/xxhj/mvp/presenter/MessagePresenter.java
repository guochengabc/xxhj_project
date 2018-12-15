package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.GetHomeMes;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Messagelpl;
import com.kongtiaoapp.xxhj.mvp.view.MessageView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class MessagePresenter extends BasePresenterLpl<MessageView,Messagelpl> {
    @Override
    protected Messagelpl getModel() {
        return new Messagelpl();
    }
    public void onResume(Activity activity,boolean param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                GetHomeMes data= (GetHomeMes) response;
                if (data.getCode()==40000){
                    getView().setList(data);
                }else if (data.getCode()==40005){
               getView().list_null();
                }else{
                    getView().error();
                    ToastUtils.showToast(activity,data.getErrormsg());
                }
            }
        });
    }
}
