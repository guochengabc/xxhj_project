package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.PersonInfolpl;
import com.kongtiaoapp.xxhj.mvp.view.PersonInfoView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class PersonInfoPresenter extends BasePresenterLpl<PersonInfoView,PersonInfolpl> {
    @Override
    protected PersonInfolpl getModel() {
        return new PersonInfolpl();
    }
    public void upPersonInfo(Activity activity, List list){
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response= (RBResponse) o;
                if (response.getCode()==40000){
                    App.sp.setRefresh("yes");
                    activity.finish();
                }else{
                    ToastUtils.showToast(activity,response.getErrormsg());
                }
            }
        });
    }
}
