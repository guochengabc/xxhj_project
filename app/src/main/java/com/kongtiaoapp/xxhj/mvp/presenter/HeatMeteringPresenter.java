package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.EnergyMeterBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.HeatMeteringlpl;
import com.kongtiaoapp.xxhj.mvp.view.HeatMeteringView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by G_XXHJ on 2017/12/22.
 */

public class HeatMeteringPresenter extends BasePresenterLpl<HeatMeteringView, HeatMeteringlpl> {
    @Override
    protected HeatMeteringlpl getModel() {
        return new HeatMeteringlpl();
    }

    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                EnergyMeterBean bean = (EnergyMeterBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getTopList(bean);
                } else if (bean.getCode() == EMPTY) {

                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
