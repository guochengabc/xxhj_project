package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.igexin.sdk.PushManager;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.LoginBean;
import com.kongtiaoapp.xxhj.bean.ProjectListBean;
import com.kongtiaoapp.xxhj.bean.UserInfo;
import com.kongtiaoapp.xxhj.mvp.module.LoginModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class Loginlpl implements LoginModule {
    /*获取个人信息*/
    @Override
    public void getUserInfo(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETUSERINFO, map));
        new GetTask<UserInfo>(activity, UserInfo.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    /**
     * 获取项目列表
     */
    @Override
    public void getProjectList(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.LOGININFO, map));
        new GetTask<ProjectListBean>(activity, ProjectListBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    /*登录界面*/
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("phone", list.get(0));
        map.put("pwd",list.get(1));
        map.put("cid", PushManager.getInstance().getClientid(activity));
        // Log.e("LoginActivity","cid==="+PushManager.getInstance().getClientid(activity));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.LOGIN, map));
        new GetTask<LoginBean>(activity, LoginBean.class, ConstantValue.HTTP_URLS + Encode_params.NoToken_Encodeparms(params), true, listener).execute();
    }
}
