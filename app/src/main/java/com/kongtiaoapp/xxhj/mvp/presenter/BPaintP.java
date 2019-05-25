package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.BTabViewBean;
import com.kongtiaoapp.xxhj.bean.EtcStatisticBean;
import com.kongtiaoapp.xxhj.bean.ChartDataBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BPaintl;
import com.kongtiaoapp.xxhj.mvp.view.BPaintView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/10/9.  变配电图表
 */

public class BPaintP extends BasePresenterLpl<BPaintView, BPaintl> {
    @Override
    protected BPaintl getModel() {
        return new BPaintl();
    }

    /**
     * 获取tab里面的内容
     */
    public void onResume(Activity activity, String param) {
        getModel().getTabView(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                BTabViewBean bean = (BTabViewBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getTabView(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().list_null();
                } else {

                }
            }
        });
    }

    /**
     * 获取图表
     */
    public void getPaint(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ChartDataBean bean = (ChartDataBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().setText(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    getView().list_error(bean.getErrormsg());
                }
            }
        });
    }

    /**
     * 获取图表
     */
    public void getFGDL(Activity activity, String param) {
        getModel().getFGDL(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EtcStatisticBean bean = (EtcStatisticBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getFGDL(bean);
                } else if (bean.getCode() == EMPTY) {
                    getView().getFGDL_null();
                } else {
                    getView().getFGDL_error(bean.getErrormsg());
                }
            }
        });
    }

}
