package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.JieYueList;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RunningRecordlpl;
import com.kongtiaoapp.xxhj.mvp.view.RunningRecordView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class RunningRecordPresenter extends BasePresenterLpl<RunningRecordView, RunningRecordlpl> {
    @Override
    protected RunningRecordlpl getModel() {
        return new RunningRecordlpl();
    }

    public void onResume(Activity activity, List list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                JieYueList info = (JieYueList) o;

                if (info.getCode() == 40000) {
                    int size = info.getResobj().size();
                    if (size == 0) {
                        return;
                    }
                    getView().success_one();
                    getView().paint_one(info);
                } else if (info.getCode() == 40005) {
                    getView().success_one();
                    getView().list_null_one();
                } else {
                    getView().error();
                }
            }
        });
    }
}
