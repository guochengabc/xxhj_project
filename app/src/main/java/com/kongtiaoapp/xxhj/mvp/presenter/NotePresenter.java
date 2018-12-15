package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Notelpl;
import com.kongtiaoapp.xxhj.mvp.view.NoteView;

/**
 * Created by xxhj_g on 2017/7/3.
 */

public class NotePresenter extends BasePresenterLpl<NoteView,Notelpl> {
    @Override
    protected Notelpl getModel() {
        return new Notelpl();
    }
    public void onResume(Activity activity){


    }

}
