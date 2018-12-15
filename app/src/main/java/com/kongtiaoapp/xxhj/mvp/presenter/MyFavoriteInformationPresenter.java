package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.MyZiXun;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MyFavoriteInformationlpl;
import com.kongtiaoapp.xxhj.mvp.view.MyFavoriteInformationView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class MyFavoriteInformationPresenter extends BasePresenterLpl<MyFavoriteInformationView,MyFavoriteInformationlpl> {
    @Override
    protected MyFavoriteInformationlpl getModel() {
        return new MyFavoriteInformationlpl();
    }
    public void onResume(Activity activity,String param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                MyZiXun data= (MyZiXun) response;
                if (data.getCode()==40000){
                    getView().setList(data);
                }else if (data.getCode()==40005){
                    ToastUtils.showToast(activity,activity.getString(R.string.no_data));
                }else{
                    ToastUtils.showToast(activity,data.getErrormsg());
                }
            }
        });
    }
}
