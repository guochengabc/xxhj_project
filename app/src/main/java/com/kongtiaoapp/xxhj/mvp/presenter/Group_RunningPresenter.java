package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Group_Runninglpl;
import com.kongtiaoapp.xxhj.mvp.view.Group_RunningView;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class Group_RunningPresenter extends BasePresenterLpl<Group_RunningView,Group_Runninglpl> {
    @Override
    protected Group_Runninglpl getModel() {
        return new Group_Runninglpl();
    }
    public void onResume(Activity activity,String param){

    }
}
