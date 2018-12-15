package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RecordSearchListBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RecordSearchl;
import com.kongtiaoapp.xxhj.mvp.view.RecordSearchFragmentV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

public class RecordSearchP extends BasePresenterLpl<RecordSearchFragmentV,RecordSearchl> {
    @Override
    protected RecordSearchl getModel() {
        return new RecordSearchl();
    }


    public void getData(Activity activity, List<String> datas) {

        getModel().getDataForservices(activity, datas, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RecordSearchListBean bean= (RecordSearchListBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setList(bean);
                }else if (bean.getCode()==EMPTY){
                    getView().list_null(activity.getString(R.string.no_data));
                }else{
                    getView().error();
                }
            }
        });
    }
}
