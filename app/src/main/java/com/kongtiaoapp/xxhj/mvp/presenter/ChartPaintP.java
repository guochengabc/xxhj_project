package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ChartPaintl;
import com.kongtiaoapp.xxhj.mvp.view.ChartPaintV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

public class ChartPaintP extends BasePresenterLpl<ChartPaintV,ChartPaintl> {
    @Override
    protected ChartPaintl getModel() {
        return new ChartPaintl();
    }

    /**
     * 请求图表数据
     * @param activity
     * @param dateStr
     * @param type
     */
    public void getRequestData(Activity activity,String dateStr,String type){
        getModel().requestData(activity, dateStr, type, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {

            }
        });
    }
}
