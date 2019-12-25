package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.bean.HomeRunningInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Main_Fragmentlpl;
import com.kongtiaoapp.xxhj.mvp.view.MainFragmentView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/6/20.   项目主页第一个fragment的presenter
 */
public class MainFragmentPresenter extends BasePresenterLpl<MainFragmentView, Main_Fragmentlpl> {
    @Override
    protected Main_Fragmentlpl getModel() {
        return new Main_Fragmentlpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                HomeRunningInfoBean bean = (HomeRunningInfoBean) response;
                HomeRunningInfoBean.ResobjBean resobj = bean.getResobj();

                if (bean.getCode() == SUCCEDD) {
                    List<HomeRunningInfoBean.ResobjBean.ModulesBean> list = resobj.getModules();
                    App.sp.setProjectId(list.get(0).getProjectId());
                } else if (bean.getCode() == EMPTY) {
                } else {
                    //  ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
