package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Informationlpl;
import com.kongtiaoapp.xxhj.mvp.view.InformationView;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class InformationPresenter extends BasePresenterLpl<InformationView,Informationlpl> {
    @Override
    protected Informationlpl getModel() {
        return new Informationlpl();
    }
}
