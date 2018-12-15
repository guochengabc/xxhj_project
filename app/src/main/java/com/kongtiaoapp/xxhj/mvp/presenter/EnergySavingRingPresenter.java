package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnergySavingRinglpl;
import com.kongtiaoapp.xxhj.mvp.view.EnergySavingRingView;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class EnergySavingRingPresenter extends BasePresenterLpl<EnergySavingRingView,EnergySavingRinglpl> {
    @Override
    protected EnergySavingRinglpl getModel() {
        return new EnergySavingRinglpl();
    }
}
