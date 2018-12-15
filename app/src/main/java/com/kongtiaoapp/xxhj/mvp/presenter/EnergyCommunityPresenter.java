package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetCommunityESMessage;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.EnergyCommunitylpl;
import com.kongtiaoapp.xxhj.mvp.view.EnergyCommunityView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class EnergyCommunityPresenter extends BasePresenterLpl<EnergyCommunityView,EnergyCommunitylpl> {
    @Override
    protected EnergyCommunitylpl getModel() {
        return new EnergyCommunitylpl();
    }
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                GetCommunityESMessage data= (GetCommunityESMessage) response;
                if (data.getCode()==40000){
                    getView().setList(data);
                }else if (data.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, data.getErrormsg());
                }

            }
        });
    }
}
