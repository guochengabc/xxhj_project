package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetDutyRecordList;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ZhiBanLeaderlpl;
import com.kongtiaoapp.xxhj.mvp.view.ZhiBanLeaderView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class ZhiBanLeaderPresenter extends BasePresenterLpl<ZhiBanLeaderView, ZhiBanLeaderlpl> {
    @Override
    protected ZhiBanLeaderlpl getModel() {
        return new ZhiBanLeaderlpl();
    }

    public void onResuem(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetDutyRecordList response = (GetDutyRecordList) o;
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
