package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AppIntroductlpl;
import com.kongtiaoapp.xxhj.mvp.view.AppIntroductView;

/**
 * Created by G_XXHJ on 2017/12/20.
 */

public class AppIntroductPresenter extends BasePresenterLpl<AppIntroductView,AppIntroductlpl> {
    @Override
    protected AppIntroductlpl getModel() {
        return new AppIntroductlpl();
    }
    public void onResume(Activity activity){

    }
}
