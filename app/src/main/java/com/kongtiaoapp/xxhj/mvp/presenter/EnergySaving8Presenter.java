package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnergySaving8lpl;
import com.kongtiaoapp.xxhj.mvp.view.EnergySaving8View;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class EnergySaving8Presenter extends BasePresenterLpl<EnergySaving8View,EnergySaving8lpl> {
    @Override
    protected EnergySaving8lpl getModel() {
        return new EnergySaving8lpl();
    }
}
