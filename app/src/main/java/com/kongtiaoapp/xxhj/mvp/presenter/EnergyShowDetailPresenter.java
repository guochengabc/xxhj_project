package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnergyShowDetail;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnergyShowDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.EnergyShowDetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class EnergyShowDetailPresenter extends BasePresenterLpl<EnergyShowDetailView, EnergyShowDetaillpl> {
    @Override
    protected EnergyShowDetaillpl getModel() {
        return new EnergyShowDetaillpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyShowDetail response= (EnergyShowDetail) o;
                if (response.getCode()==40000){
                    getView().setList(response);
                }else if (response.getCode()==40005){
                    ToastUtils.showToast(activity,activity.getString(R.string.no_data));
                }else{
                    ToastUtils.showToast(activity,response.getErrormsg());
                }
            }
        });

    }

    public void favorite(Activity activity, List param) {
        getModel().favorate(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response= (RBResponse) o;
                if (response.getCode()==40000){
                    getView().favorites();
                }else if (response.getCode()==40005){
                ToastUtils.showToast(activity,activity.getString(R.string.no_data));
            }else{
                ToastUtils.showToast(activity,response.getErrormsg());
            }
            }
        });
    }
}
