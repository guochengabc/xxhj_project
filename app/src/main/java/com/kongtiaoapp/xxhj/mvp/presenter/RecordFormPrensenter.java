package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RecordFormDeviceBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RecordFormlpl;
import com.kongtiaoapp.xxhj.mvp.view.RecordFormView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/8/25.
 */

public class RecordFormPrensenter extends BasePresenterLpl<RecordFormView,RecordFormlpl> {
    @Override
    protected RecordFormlpl getModel() {
        return new RecordFormlpl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RecordFormDeviceBean bean= (RecordFormDeviceBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setText(bean);
                }
            }
        });
    }
}
