package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.Report_Running_RecordBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ReportWorklpl;
import com.kongtiaoapp.xxhj.mvp.view.ReportWorkView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.common.Callback;

import java.io.File;
import java.util.List;

/**
 * Created by xxhj_g on 2017/8/26.
 */

public class ReportWorkPresenter extends BasePresenterLpl<ReportWorkView,ReportWorklpl> {
    @Override
    protected ReportWorklpl getModel() {
        return new ReportWorklpl();
    }
    public void onResume(Activity activity,List param){
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                Report_Running_RecordBean bean= (Report_Running_RecordBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().getDataSuccedd(bean);
                }else if (bean.getCode()==EMPTY){

                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
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
