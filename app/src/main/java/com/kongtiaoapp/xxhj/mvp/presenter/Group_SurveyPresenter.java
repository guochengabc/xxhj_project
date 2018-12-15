package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.GetGroupProjectListBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Group_Surveylpl;
import com.kongtiaoapp.xxhj.mvp.view.Group_SurveyView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class Group_SurveyPresenter extends BasePresenterLpl<Group_SurveyView,Group_Surveylpl> {
    @Override
    protected Group_Surveylpl getModel() {
        return new Group_Surveylpl();
    }
    public void onResume(Activity activity, List list){
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {
                getView().closeDrawer();
            }

            @Override
            public void requestSuccess(Object o) {
                getView().closeDrawer();
                GetGroupProjectListBean bean= (GetGroupProjectListBean) o;
                if (bean.getCode() == 40000) {
                   getView().setList(bean);
                } else if (bean.getCode() == 40005) {
                   getView().setText("");
                }
            }
        });

    }

}
