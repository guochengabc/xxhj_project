package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.InspectionBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Inspectionlpl;
import com.kongtiaoapp.xxhj.mvp.view.InspectionView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/4/29.
 */

public class InspectionPresenter extends BasePresenterLpl<InspectionView,Inspectionlpl> {
    @Override
    protected Inspectionlpl getModel() {
        return new Inspectionlpl();
    }
    public void onResume(Activity activity,String param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                InspectionBean bean= (InspectionBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setList(bean);
                }else if (bean.getCode()==EMPTY){

                }else{

                }
            }
        });
    }
    public void commitInsp(Activity activity, List param){
        getModel().commitInsp(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean= (RBResponse) o;
                if (bean.getCode()==SUCCEDD){
                    getView().commitInsp("");
                    ToastUtils.showToast(activity,activity.getString(R.string.commit_succeed));
                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }
}
