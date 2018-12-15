package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.Energy8List;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Energy8Informationlpl;
import com.kongtiaoapp.xxhj.mvp.view.Energy8InformationView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/9.
 */
public class Energy8InformationPresenter extends BasePresenterLpl<Energy8InformationView, Energy8Informationlpl> {
    @Override
    protected Energy8Informationlpl getModel() {
        return new Energy8Informationlpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                Energy8List bean = (Energy8List) response;
                if (bean.getCode() == 40000) {
                    getView().setList(bean);
                } else if (bean.getCode() == 40005) {
                    getView().list_null();
                    ToastUtils.showToast(activity, activity.getString(R.string.all_load));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
