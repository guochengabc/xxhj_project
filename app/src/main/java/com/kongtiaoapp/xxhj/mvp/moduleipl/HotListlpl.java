package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.Moments;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.HotListModule;
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
public class HotListlpl implements HotListModule {
    @Override
    public void praise_module(Activity activity, Object data, ResponseXXHJListener listener) {
        List list= (List) data;
        List<Moments.MomentInfo> mList= (List<Moments.MomentInfo>) list.get(0);
        int position= (int) list.get(1);
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("msgId", mList.get(position).getMessageId());
        map.put("type",list.get(2).toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.WELLDONE, map));
        new GetTask<RBResponse>(activity,RBResponse.class,ConstantValue.HTTP_URLS+Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }

    @Override
    public void favorite_module(Activity activity, Object data, ResponseXXHJListener listener) {
        List list= (List) data;
        List<Moments.MomentInfo> mList= (List<Moments.MomentInfo>) list.get(0);
        int position= (int) list.get(1);
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("msgId", mList.get(position).getMessageId());
        map.put("action", list.get(2).toString());
        map.put("type", ConstantValue.MOMENT);
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.COLLECTMESSAGE, map));
        new GetTask<RBResponse>(activity,RBResponse.class,ConstantValue.HTTP_URLS+Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("currentPage", list.get(0));
        Map<String, String> params = new HashMap<String, String>();
        String from = list.get(1);
        if (ConstantValue.COMMUNITY_HOT.equals(from)) {
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETHOTESMESSAGE, map));
        } else if (ConstantValue.COMMUNITY_SAME_CITY.equals(from)) {
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETSAMECITYESMESSAGE, map));
        } else if (ConstantValue.COMMUNITY_SAME_HANG.equals(from)) {
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETSAMEWORKESMESSAGE, map));
        }
        new GetTask<Moments>(activity, Moments.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
