package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.moduleipl.RepairPeoplelpl;

/**
 * Created by xxhj_g on 2017/7/21.
 */

public class RepairPeoplePresneter extends BasePresenterLpl<BaseView,RepairPeoplelpl> {
    @Override
    protected RepairPeoplelpl getModel() {
        return new RepairPeoplelpl();
    }
    public void onResume(Activity activity,String param){

    }
}
