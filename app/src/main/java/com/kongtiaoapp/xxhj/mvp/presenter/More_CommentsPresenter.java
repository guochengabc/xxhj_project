package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.More_Commentslpl;
import com.kongtiaoapp.xxhj.mvp.view.More_CommentsView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class More_CommentsPresenter extends BasePresenterLpl<More_CommentsView, More_Commentslpl> {
    @Override
    protected More_Commentslpl getModel() {
        return new More_Commentslpl();
    }

    public void onResume(Activity activity, List list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                CommentsGroupBean response= (CommentsGroupBean) o;
                if (response.getCode() == 40000) {
                   getView().setText(response);
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                    getView().notifyAdapter();
                } else {
                    ToastUtils.showToast(activity, response.getErrormsg());
                }
            }
        });
    }

    public void commit_Comments(Activity activity, List param) {
            getModel().commit_Comments(activity, param, new ResponseXXHJListener() {
                @Override
                public void requuestError(int code) {

                }

                @Override
                public void requestSuccess(Object o) {
                    RBResponse response= (RBResponse) o;
                    if (response.getCode() == 40000) {
                      getView().commit_Comments_View(response);
                    } else {
                        ToastUtils.showToast(activity, response.getErrormsg());
                    }
                }
            });
    }

    public void delete_Comments(Activity activity, String param) {
        getModel().delete_Comments(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse bean= (RBResponse) o;
                if (bean.getCode() == 40000) {
                    ToastUtils.showToast(activity, "删除成功");
                   getView().delete_Comments_View(bean);
                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }

}
