package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Patrollpl;
import com.kongtiaoapp.xxhj.mvp.view.PatrolView;

/**
 * Created by xxhj_g on 2017/6/24.
 */

public class PatrolPresenter extends BasePresenterLpl<PatrolView,Patrollpl> {
    @Override
    protected Patrollpl getModel() {
        return new Patrollpl();
    }
}
