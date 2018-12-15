package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BDataEntryDetaill;
import com.kongtiaoapp.xxhj.mvp.view.BDataEntryDetailV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/9/29.
 */

public class BDataEntryDetailP extends BasePresenterLpl<BDataEntryDetailV,BDataEntryDetaill> {
    @Override
    protected BDataEntryDetaill getModel() {
        return new BDataEntryDetaill();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                List<RunningParam> response = (List<RunningParam>) o;
                if (response != null && response.size() > 0) {

                    try {
                        getView().setList(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showToast(activity, "您点击的太快了！");
                    }
                } else {

                }

            }
        });
    }

    /**
     * 获取铭牌信息
     */
    public void getNameplate(Activity activity, String param) {
        getModel().GetNameplateParaInfo(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                List<RunningParam> response = (List<RunningParam>) o;
                if (response != null && response.size() > 0) {

                    try {
                        getView().setList(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showToast(activity, "您点击的太快了！");
                    }
                } else {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                }

            }
        });
    }

    public void saveDrag_CommitData(Activity activity, List param) {
        getModel().saveDrag_CommitData(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().saveDrag_CommitData_View(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void commitData(Activity activity, String param) {
        getModel().commitData(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean = (RBResponse) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().commitData("");
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }
}
