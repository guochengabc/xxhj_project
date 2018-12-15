package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Work_RFlpl;
import com.kongtiaoapp.xxhj.mvp.view.Work_RFView;

/**
 * Created by xxhj_g on 2017/8/25.   工单记录表 以后要用到的
 */

public class Work_RFPresenter extends BasePresenterLpl<Work_RFView, Work_RFlpl> {
    @Override
    protected Work_RFlpl getModel() {
        return new Work_RFlpl();
    }

    public void onResume(Activity activity, String param) {

    }
}
