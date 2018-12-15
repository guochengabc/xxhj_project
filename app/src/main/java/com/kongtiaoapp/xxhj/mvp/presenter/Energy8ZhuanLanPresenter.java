package com.kongtiaoapp.xxhj.mvp.presenter;

import android.app.Activity;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetSpecialArticleList;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.moduleipl.Energy8ZhuanLanlpl;
import com.kongtiaoapp.xxhj.mvp.view.Energy8ZhuanLanView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class Energy8ZhuanLanPresenter extends BasePresenterLpl<Energy8ZhuanLanView,Energy8ZhuanLanlpl> {
    @Override
    protected Energy8ZhuanLanlpl getModel() {
        return new Energy8ZhuanLanlpl();
    }
    public void onResume(Activity activity, List list){
        getModel().getDataForservices(activity, list, new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                GetSpecialArticleList dict = (GetSpecialArticleList) o;
                if (dict.getCode() == 40000) {
                   getView().setList(dict);
                } else if (dict.getCode() == 40005) {
                    ToastUtils.showToast(activity,activity.getString(R.string.no_data));
                } else {
                    ToastUtils.showToast(activity,dict.getErrormsg());
                }
            }
        });
    }
}
