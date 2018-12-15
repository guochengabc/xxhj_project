package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ModifyPWDlpl;
import com.kongtiaoapp.xxhj.mvp.view.ModifyPWDView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class ModifyPWDPresenter extends BasePresenterLpl<ModifyPWDView,ModifyPWDlpl> {
    @Override
    protected ModifyPWDlpl getModel() {
        return new ModifyPWDlpl();
    }
    public void onResume(Activity activity, List list){
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse rbResponse= (RBResponse) o;
                if (rbResponse.getCode() == 40000) {
                    ToastUtils.showToast(activity, activity.getString(R.string.modify_pwd_success));
                } else if (rbResponse.getCode() == 40005) {

                } else {
                    ToastUtils.showToast(activity, rbResponse.getErrormsg());
                }
            }
        });
    }
}
