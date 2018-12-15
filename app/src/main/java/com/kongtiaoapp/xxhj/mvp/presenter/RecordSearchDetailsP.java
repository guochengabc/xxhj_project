package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RecordDetailsBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RecordSearchDetailsl;
import com.kongtiaoapp.xxhj.mvp.view.RecordSearchDetailsV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.recordform.activity.RecordSearchDetailsActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class RecordSearchDetailsP extends BasePresenterLpl<RecordSearchDetailsV, RecordSearchDetailsl> {
    @Override
    protected RecordSearchDetailsl getModel() {
        return new RecordSearchDetailsl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RecordDetailsBean bean = (RecordDetailsBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getList(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    getView().list_error();
                }
            }
        });
    }

    public void recallData(Activity activity, List param) {
        getModel().recallData(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().recallData();
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    getView().list_error();
                }
            }
        });
    }

    /**
     * 修改数据
     */
    public void modifyData(RecordSearchDetailsActivity activity, List<RecordDetailsBean.ResobjBean> list, ArrayList<ImageItem> selImageList, List param, String recordStatus) {
        getModel().modifyData(activity, list, selImageList, param, recordStatus, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().modifyData();
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {
                    getView().list_error();
                }
            }
        });
    }
}
