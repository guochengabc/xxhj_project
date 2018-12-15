package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RecordFormDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.RecordFormDetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/6. 记录表单具体的设备
 */

public class RecordFormDetailPresenter extends BasePresenterLpl<RecordFormDetailView, RecordFormDetaillpl> {
    @Override
    protected RecordFormDetaillpl getModel() {
        return new RecordFormDetaillpl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {

            }
        });
    }
}
