package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.getUserList;
import com.kongtiaoapp.xxhj.mvp.module.UserListModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class UserListlpl implements UserListModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("type", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        if (data.toString().equals("CCC")) {
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDISPATCHERINFO, map));
        } else {
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETUSERINFOLIST, map));
        }
        new GetTask<getUserList>(activity, getUserList.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
