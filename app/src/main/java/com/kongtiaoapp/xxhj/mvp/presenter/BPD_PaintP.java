package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.ChartDataBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BPD_Paintl;
import com.kongtiaoapp.xxhj.mvp.view.BPD_PaintV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/9/11.
 */

public class BPD_PaintP extends BasePresenterLpl<BPD_PaintV,BPD_Paintl> {
    @Override
    protected BPD_Paintl getModel() {
        return new BPD_Paintl();
    }
    public void onResume(Activity activity,List param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ChartDataBean bean= (ChartDataBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setText(bean);
                }else if (bean.getCode()==EMPTY){

                }else{

                }
            }
        });
    }
}
