package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EcodeCreatel;
import com.kongtiaoapp.xxhj.mvp.view.EcodeCreateV;

/**
 * Created by G_XXHJ on 2018/7/17.
 */

public class EcodeCreateP extends BasePresenterLpl<EcodeCreateV,EcodeCreatel> {
    @Override
    protected EcodeCreatel getModel() {
        return new EcodeCreatel();
    }
}
