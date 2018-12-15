package com.kongtiaoapp.xxhj.mvp.presenter;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AboutUsActivity;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetAboutUs;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AboutUsModulelpl;
import com.kongtiaoapp.xxhj.mvp.view.AboutUsView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class AboutAsPresenter extends BasePresenterLpl<AboutUsView, AboutUsModulelpl> {
    @Override
    protected AboutUsModulelpl getModel() {
        return new AboutUsModulelpl();
    }

    public void OnResume(AboutUsActivity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetAboutUs data = (GetAboutUs) o;
                GetAboutUs.AboutUs resobj = data.getResobj();
                if (data.getCode() == 40000) {
                    getView().setText(resobj);
                    getView().loadImageView(ConstantValue.URL +resobj.getLogoUrl());
                } else if (data.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getResources().getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }
            }
        });
    }
}
