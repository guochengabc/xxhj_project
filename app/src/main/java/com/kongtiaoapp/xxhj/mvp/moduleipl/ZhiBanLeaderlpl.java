package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.GetDutyRecordList;
import com.kongtiaoapp.xxhj.mvp.module.ZhiBanLeaderModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class ZhiBanLeaderlpl implements ZhiBanLeaderModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

        Map<String, String> map = new HashMap<>();
        map.put("uid", App.sp.getUid());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETLEADERDUTYRECORDS, map));
        new GetTask<GetDutyRecordList>(activity, GetDutyRecordList.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
