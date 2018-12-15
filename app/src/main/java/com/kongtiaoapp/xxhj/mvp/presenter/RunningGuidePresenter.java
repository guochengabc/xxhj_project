package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RunningGuigeBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RunningGuidelpl;
import com.kongtiaoapp.xxhj.mvp.view.RunningGuideView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by G_XXHJ on 2017/12/14.
 */

public class RunningGuidePresenter extends BasePresenterLpl<RunningGuideView,RunningGuidelpl> {
    @Override
    protected RunningGuidelpl getModel() {
        return new RunningGuidelpl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RunningGuigeBean bean= (RunningGuigeBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().getList(bean.getResobj());
                }else if (bean.getCode()==EMPTY){
                    getView().list_null();
                }else{
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
