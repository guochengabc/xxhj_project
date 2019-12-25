package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.bean.ModuleBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MainModuleIpl;
import com.kongtiaoapp.xxhj.mvp.view.MainView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/4/25.
 */
public class MainPresenter extends BasePresenterLpl<MainView, MainModuleIpl> {
    @Override
    protected MainModuleIpl getModel() {
        return new MainModuleIpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                getView().VersionCheck(o);
            }
        });
    }

    public void getModule(Activity activity) {
        getModel().getModule(activity, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ModuleBean bean = (ModuleBean) o;
                ModuleBean.ResobjBean resobj = bean.getResobj();
                Log.i("首页========",resobj.isCommon()+"===");
                if (resobj != null) {
                    App.sp.setCommonNum(resobj.isCommon());
                }
                if (bean.getCode() == SUCCEDD) {
                    getView().getModule(o);
                } else if (bean.getCode() == EMPTY) {

                } else {

                }

            }
        });
    }

}
