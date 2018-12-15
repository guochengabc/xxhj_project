package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AlterSchoollpl;
import com.kongtiaoapp.xxhj.mvp.view.AlterSchoolView;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class AlterSchoolPresenter extends BasePresenterLpl<AlterSchoolView,AlterSchoollpl> {
    @Override
    protected AlterSchoollpl getModel() {
        return new AlterSchoollpl();
    }
}
