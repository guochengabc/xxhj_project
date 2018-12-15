package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BPDlpl;
import com.kongtiaoapp.xxhj.mvp.view.BPDView;

/**
 * Created by xxhj_g on 2017/8/25.   变配电记录
 */

public class BPDPresenter extends BasePresenterLpl<BPDView,BPDlpl> {
    @Override
    protected BPDlpl getModel() {
        return new BPDlpl();
    }
    public void onResume(Activity activity,String param){

    }
}
