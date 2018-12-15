package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.ZhenDuanList;
import com.kongtiaoapp.xxhj.mvp.module.ZhenDuanRecordModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class ZhenDuanRecordlpl implements ZhenDuanRecordModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("projectId", list.get(0));
        map.put("currentPage",list.get(1));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDIAGDATALIST, map));
        new GetTask<ZhenDuanList>(activity,ZhenDuanList.class,ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }
}
