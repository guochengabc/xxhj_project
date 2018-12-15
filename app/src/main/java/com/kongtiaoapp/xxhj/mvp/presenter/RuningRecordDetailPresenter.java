package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.GetEnergyInfo;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RunningRecordDetaillpl;
import com.kongtiaoapp.xxhj.mvp.view.RunningRecordDetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class RuningRecordDetailPresenter extends BasePresenterLpl<RunningRecordDetailView,RunningRecordDetaillpl> {
    @Override
    protected RunningRecordDetaillpl getModel() {
        return new RunningRecordDetaillpl();
    }
    public void onResume(Activity activity, List list){
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetEnergyInfo response= (GetEnergyInfo) o;

                if (response.getCode() == 40000) {
                        getView().setList(response);
                    }
            }
        });
    }

}
