package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.FriendMesgList;
import com.kongtiaoapp.xxhj.mvp.module.HaoYouMsgModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class HaoYouMsglpl implements HaoYouMsgModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("currentPage", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETFRIENDMESSAGEINFO, map));
        new GetTask<FriendMesgList>(activity, FriendMesgList.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
