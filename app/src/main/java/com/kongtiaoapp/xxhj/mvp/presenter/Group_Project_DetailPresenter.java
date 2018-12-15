package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.HVAC_NewProjectDetailBean;
import com.kongtiaoapp.xxhj.bean.HVAC_ProjectDetailBean;
import com.kongtiaoapp.xxhj.bean.PaintAnylizeBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Group_Project_DetailModuleIpl;
import com.kongtiaoapp.xxhj.mvp.view.Group_Project_DetailView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/4/28.
 */
public class Group_Project_DetailPresenter extends BasePresenterLpl<Group_Project_DetailView, Group_Project_DetailModuleIpl> {
    @Override
    protected Group_Project_DetailModuleIpl getModel() {
        return new Group_Project_DetailModuleIpl();
    }

    public void onResumeAll(Activity activity, List list) {
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                HVAC_ProjectDetailBean bean = (HVAC_ProjectDetailBean) o;
                if (bean.getCode() == 40000) {
                    getView().setText(o);
                } else if (bean.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });

    }
    public void onResumeAll_New(Activity activity, List list) {
        getModel().new_hvav_main(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                HVAC_NewProjectDetailBean bean = (HVAC_NewProjectDetailBean) o;
                if (bean.getCode() == 40000) {
                    getView().setText(o);
                } else if (bean.getCode() == 40005) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });

    }

    /*评论区列表*/
    public void onCommentList(Activity activity) {
        getModel().getCommitList(activity, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                getView().getCommitList(o);
            }
        });
    }

    public void onSubmit(Activity activity, String content, String toUid) {
        getModel().postCommit(activity, content, toUid, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                getView().postCommit(o);
            }
        });
    }

    //删除评论区
    public void onDeleteCommitList(Activity activity, String commentId) {
        getModel().deleteCommitList(activity, commentId, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                getView().deleteCommitList(o);
            }
        });
    }
    //获取图表分析诊断

    public void getPaintAnyize(Activity activity, String category) {
        getModel().getPaintAnylize(activity, category, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                PaintAnylizeBean bean = (PaintAnylizeBean) o;
                if (bean.getCode() == SUCCEDD) {
                    getView().getPaintAnylize(bean.getResobj());
                } else if (bean.getCode() == EMPTY) {
                    ToastUtils.showToast(activity, activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity, bean.getErrormsg());
                }
            }
        });
    }

}
