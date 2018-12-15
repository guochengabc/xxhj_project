package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.SysMesgList;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ZhenDuanMsglpl;
import com.kongtiaoapp.xxhj.mvp.view.ZhenDuanMsgView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class ZhenDuanMsgPresenter extends BasePresenterLpl<ZhenDuanMsgView, ZhenDuanMsglpl> {
    @Override
    protected ZhenDuanMsglpl getModel() {
        return new ZhenDuanMsglpl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                SysMesgList response = (SysMesgList) o;
                if (response.getCode() == 40000) {
                    getView().setList(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }
}
