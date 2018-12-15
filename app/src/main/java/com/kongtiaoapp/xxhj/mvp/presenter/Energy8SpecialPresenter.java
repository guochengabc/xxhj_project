package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.Energy8List;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Energy8Speciallpl;
import com.kongtiaoapp.xxhj.mvp.view.Energy8SpecialView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/17.
 */
public class Energy8SpecialPresenter extends BasePresenterLpl<Energy8SpecialView,Energy8Speciallpl> {
    @Override
    protected Energy8Speciallpl getModel() {
        return new Energy8Speciallpl();
    }
    public void onResume(Activity activity, List list){
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object response) {
                Energy8List data= (Energy8List) response;
                    if (data.getCode()==40000){
                        getView().setList(data);
                    }else if (data.getCode()==40005){
                        ToastUtils.showToast(activity, activity.getString(R.string.all_load));
                    }else{
                        ToastUtils.showToast(activity,data.getErrormsg());
                    }
            }
        });
    }
}
