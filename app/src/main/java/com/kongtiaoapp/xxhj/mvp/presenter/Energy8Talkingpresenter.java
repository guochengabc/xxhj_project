package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.GetPostList;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Energy8Talkinglpl;
import com.kongtiaoapp.xxhj.mvp.view.Energy8TalkingView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class Energy8Talkingpresenter extends BasePresenterLpl<Energy8TalkingView,Energy8Talkinglpl> {
    @Override
    protected Energy8Talkinglpl getModel() {
        return new Energy8Talkinglpl();
    }
    public void onResume(Activity activity, List list){
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                GetPostList data= (GetPostList) response;
                if (data.getCode()==40000){
                    getView().setList(data);
                }else if (data.getCode()==40005){
                    getView().list_null();
                }else{
                    ToastUtils.showToast(activity,data.getErrormsg());
                }

            }
        });
    }
}
