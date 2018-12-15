package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.BWeekReportBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BWeek_MonthReportl;
import com.kongtiaoapp.xxhj.mvp.view.BWeek_MonthReportV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/9/14.  变配电  周报/月报
 */

public class BWeek_MonthReportP extends BasePresenterLpl<BWeek_MonthReportV,BWeek_MonthReportl> {
    @Override
    protected BWeek_MonthReportl getModel() {
        return new BWeek_MonthReportl();
    }
    public void onResume(Activity activity,String param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                BWeekReportBean bean= (BWeekReportBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setList(bean);
                }else if (bean.getCode()==EMPTY){
                    getView().listNull();
                }else{

                }
            }
        });
    }

}
