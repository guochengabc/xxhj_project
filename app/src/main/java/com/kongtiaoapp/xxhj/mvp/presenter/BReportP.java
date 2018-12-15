package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetRDateParam_RecorderBean;
import com.kongtiaoapp.xxhj.bean.ProjectList;
import com.kongtiaoapp.xxhj.bean.Project_Module_Name;
import com.kongtiaoapp.xxhj.bean.Report_Running_RecordBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.BReportl;
import com.kongtiaoapp.xxhj.mvp.view.BReportV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.common.Callback;

import java.io.File;
import java.util.List;

/**
 * Created by G_XXHJ on 2018/9/13.  变配电  报表导出
 */

public class BReportP extends BasePresenterLpl<BReportV,BReportl> {
    @Override
    protected BReportl getModel() {
        return new BReportl();
    }
    public void onResume(Activity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                GetRDateParam_RecorderBean data= (GetRDateParam_RecorderBean) response;
                if (data.getCode()==40000){
                    getView().setList(data);
                }else if (data.getCode()==40005){
                    getView().list_null("0");
                }else{
                    getView().onError("0");
                    ToastUtils.showToast(activity,data.getErrormsg());
                }
            }
        });
    }

    public void getProjectName(Activity activity, String param) {
        getModel().getProjectName(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                ProjectList data= (ProjectList) response;
                if (data.getCode()==40000){
                    getView().getProjectName_View(data);
                }else if (data.getCode()==40005){
                    getView().list_null("1");
                    ToastUtils.showToast(activity,activity.getString(R.string.no_data));
                }else{
                    getView().onError("1");
                    ToastUtils.showToast(activity,data.getErrormsg());
                }
            }
        });
    }

    public void getProjectModule(Activity activity, String param) {
        getModel().getProjectModule(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                Project_Module_Name data= (Project_Module_Name) response;
                if (data.getCode()==40000){
                    getView().getProjectModule_View(data);
                }else if (data.getCode()==40005){
                    getView().list_null("1");
                }else{
                    ToastUtils.showToast(activity,data.getErrormsg());
                }
            }
        });
    }

    public void report_data_forservice(Activity activity, List<String> list) {
        getModel().report_data_forservice(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                Report_Running_RecordBean data= (Report_Running_RecordBean) response;
                if (data.getCode()==40000){
                    getView().report_data_forservice_View(data);
                }else if (data.getCode()==40005){
                    ToastUtils.showToast(activity,activity.getString(R.string.running_recorder_null));
                }else{
                    ToastUtils.showToast(activity,data.getErrormsg());
                }
            }
        });
    }

    public void downDocument( List<String> list) {
        getModel().downDocument(list, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                getView().onLoading(total,current,isDownloading);
            }

            @Override
            public void onSuccess(File result) {
                getView().succeed();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                getView().onError("4");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                getView().onCancelled();
            }

            @Override
            public void onFinished() {
                getView().onfinish("");
            }
        });
    }
}
