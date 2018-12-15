package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AlterCompanyModuleIpl;
import com.kongtiaoapp.xxhj.mvp.view.AlterCompanyView;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class AlterCompanyPresenter extends BasePresenterLpl<AlterCompanyView,AlterCompanyModuleIpl> {
    @Override
    protected AlterCompanyModuleIpl getModel() {
        return new AlterCompanyModuleIpl();
    }
}
