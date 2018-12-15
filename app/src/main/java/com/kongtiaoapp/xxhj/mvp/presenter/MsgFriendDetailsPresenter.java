package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.GetFriendInfo;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.MsgFriendDetailslpl;
import com.kongtiaoapp.xxhj.mvp.view.MsgFriendDetailsView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class MsgFriendDetailsPresenter extends BasePresenterLpl<MsgFriendDetailsView, MsgFriendDetailslpl> {
    @Override
    protected MsgFriendDetailslpl getModel() {
        return new MsgFriendDetailslpl();
    }

    public void onResume(Activity activity, String param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetFriendInfo response = (GetFriendInfo) o;
                if (response.getCode() == 40000) {
                    GetFriendInfo data = (GetFriendInfo) response;
                    getView().setText(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, "没有获取到用户详情");
                }
            }
        });
    }

    public void PingBi(Activity activity, List param) {
        getModel().PingBi(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                if (response.getCode() == 40000) {
                    getView().PingBi_View(response);
                } else {
                    ToastUtils.showToast(activity, "操作失败");
                }
            }
        });
    }

    public void delete(Activity activity, String param) {
        getModel().delete(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                //删除好友
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(activity, "已删除");
                } else {
                    ToastUtils.showToast(activity, "操作失败");
                }
            }
        });
    }

    public void addFriend(Activity activity, String param) {
        getModel().addFriend(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                //添加好友
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(activity, "已发出申请,等待好友确认");
                } else {
                    ToastUtils.showToast(activity, "操作失败");
                }
            }
        });
    }

    public void apply(Activity activity, String param) {
        getModel().apply(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse response = (RBResponse) o;
                //同意添加好友
                if (response.getCode() == 40000) {
                    ToastUtils.showToast(activity, "已同意");
                } else {
                    ToastUtils.showToast(activity, "操作失败");
                }
            }
        });
    }
}
