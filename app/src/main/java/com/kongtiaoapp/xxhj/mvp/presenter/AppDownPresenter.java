package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.bean.VersionBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.AppDownlpl;
import com.kongtiaoapp.xxhj.mvp.view.AppDownView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/23.
 */
public class AppDownPresenter extends BasePresenterLpl<AppDownView,AppDownlpl> {
    @Override
    protected AppDownlpl getModel() {
        return new AppDownlpl();
    }
    //获取二维码
    public void onResume(Activity activity){
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                VersionBean bean= (VersionBean) o;
                if (bean.getCode()==SUCCEDD){
                    getView().setText(bean);
                }else if (bean.getCode()==EMPTY){

                }else{
                    ToastUtils.showToast(activity,"生成数据有问题，稍后尝试！");
                }
            }
        });
    }
}
