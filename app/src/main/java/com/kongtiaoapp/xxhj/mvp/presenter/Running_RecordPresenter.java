package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Running_Recordlpl;
import com.kongtiaoapp.xxhj.mvp.view.Running_RecordView;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class Running_RecordPresenter extends BasePresenterLpl<Running_RecordView,Running_Recordlpl> {
    @Override
    protected Running_Recordlpl getModel() {
        return new Running_Recordlpl();
    }
}
