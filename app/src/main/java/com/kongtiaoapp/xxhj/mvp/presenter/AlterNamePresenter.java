package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AlterNamelpl;
import com.kongtiaoapp.xxhj.mvp.view.AlterNameView;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class AlterNamePresenter extends BasePresenterLpl<AlterNameView,AlterNamelpl> {
    @Override
    protected AlterNamelpl getModel() {
        return new AlterNamelpl();
    }
}
