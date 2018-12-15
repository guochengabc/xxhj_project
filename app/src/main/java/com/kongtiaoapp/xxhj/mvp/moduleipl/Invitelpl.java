package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.InviteCodeBean;
import com.kongtiaoapp.xxhj.mvp.module.InviteModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class Invitelpl implements InviteModule {
    @Override
    public void resetInvateCode(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("pwd", MD5Utils.String2MD5(data.toString()));
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.RESETINVITECODE, map));
        new GetTask<InviteCodeBean>(activity, InviteCodeBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        //获取邀请码
        Map<String, String> map = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETMYINVITECODE, map));
        new GetTask<InviteCodeBean>(activity, InviteCodeBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }
}
