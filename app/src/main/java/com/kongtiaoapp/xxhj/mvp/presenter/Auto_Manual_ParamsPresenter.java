package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.Auto_Manual_ParamsBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Auto_Manual_Paramslpl;
import com.kongtiaoapp.xxhj.mvp.view.Auto_Manual_ParamsView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class Auto_Manual_ParamsPresenter extends BasePresenterLpl<Auto_Manual_ParamsView, Auto_Manual_Paramslpl> {
    @Override
    protected Auto_Manual_Paramslpl getModel() {
        return new Auto_Manual_Paramslpl();
    }

    public void onResume(Activity activity, int page) {
        getModel().getDataForservices(activity, page+"", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                Auto_Manual_ParamsBean bean = (Auto_Manual_ParamsBean) o;
                if (bean.getCode() == 40000) {
                    getView().setText(bean);
                } else if (bean.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getResources().getString(R.string.no_params));
                    getView().canClick(true);
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                    getView().canClick(true);
                }
            }
        });
    }
    public void sendPageNext(Activity activity, List list){
        getModel().setdataNextpage(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response= (RBResponse) o;
                if (response.getCode()==40000){
                    getView().sendPageNext();
                }else if (response.getCode()==40005){

                }else{
                    ToastUtils.showToast(activity,response.getErrormsg());
                }
            }
        });
    }
}
