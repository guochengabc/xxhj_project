package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ECodeListBean;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.LongPressl;
import com.kongtiaoapp.xxhj.mvp.view.LongPressV;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/7/17.长按识别图中二维码
 */

public class LongPressP extends BasePresenterLpl<LongPressV, LongPressl> {
    @Override
    protected LongPressl getModel() {
        return new LongPressl();
    }

    public void onResume(Activity activity, List param) {
        getModel().getDataForservices(activity, param, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                ECodeListBean bean= (ECodeListBean) o;
                if (bean.getCode()==40000){
                    getView().setList(bean.getResobj());
                }else if (bean.getCode()==40005){
                    ToastUtils.showToast(activity,activity.getString(R.string.no_data));
                }else{
                    ToastUtils.showToast(activity,bean.getErrormsg());
                }
            }
        });
    }
}
