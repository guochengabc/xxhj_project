package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.SEquitmentStatuslpl;
import com.kongtiaoapp.xxhj.mvp.view.SEquitmentStatusView;

/**
 * Created by xxhj_g on 2017/8/28.
 */

public class SEquitmentStatusPresenter extends BasePresenterLpl<SEquitmentStatusView, SEquitmentStatuslpl> {
    @Override
    protected SEquitmentStatuslpl getModel() {
        return new SEquitmentStatuslpl();
    }

    public void onResume(Activity activity, String param) {

    }
}
