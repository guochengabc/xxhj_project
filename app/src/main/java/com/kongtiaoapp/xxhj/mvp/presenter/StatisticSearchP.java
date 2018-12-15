package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.StatisticSearchl;
import com.kongtiaoapp.xxhj.mvp.view.StatisticSearchV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by G_XXHJ on 2018/11/22.   统计查询
 */

public class StatisticSearchP extends BasePresenterLpl<StatisticSearchV, StatisticSearchl> {
    @Override
    protected StatisticSearchl getModel() {
        return new StatisticSearchl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {

            }
        });
    }
}
