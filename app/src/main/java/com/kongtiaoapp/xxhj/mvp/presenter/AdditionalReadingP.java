package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AdditionalReadingl;
import com.kongtiaoapp.xxhj.mvp.view.AdditionalReadingV;

/**
 * Created by G_XXHJ on 2018/11/22.  补录
 */

public class AdditionalReadingP extends BasePresenterLpl<AdditionalReadingV,AdditionalReadingl> {
    @Override
    protected AdditionalReadingl getModel() {
        return new AdditionalReadingl();
    }
    public void onResume(Activity activity){

    }
}
