package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Energy8Listlpl;
import com.kongtiaoapp.xxhj.mvp.view.Energy8ListVeiw;

/**
 * Created by xxhj_g on 2017/5/13.
 */
public class Energy8ListPresenter extends BasePresenterLpl<Energy8ListVeiw,Energy8Listlpl> {
    @Override
    protected Energy8Listlpl getModel() {
        return new Energy8Listlpl();
    }
}
