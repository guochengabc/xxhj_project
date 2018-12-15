package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.ParamSettingBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ParamSettingl;
import com.kongtiaoapp.xxhj.mvp.view.ParamSettingV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/6/8.
 */

public class ParamSettingP extends BasePresenterLpl<ParamSettingV, ParamSettingl> {
    @Override
    protected ParamSettingl getModel() {
        return new ParamSettingl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ParamSettingBean bean = (ParamSettingBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setList(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().listNull();
                } else {
                    getView().listNull();
                }
            }
        });
    }
}
