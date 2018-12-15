package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.GetDutyRecordList;
import com.kongtiaoapp.xxhj.mvp.module.HistoryModule;
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
public class Historylpl implements HistoryModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("currentPage", list.get(0));
        if (!TextUtils.isEmpty( list.get(1))) {
            map.put("startDate",  list.get(1));
        }
        if (!TextUtils.isEmpty( list.get(2))) {
            map.put("endDate",  list.get(2));
        }
        if (!TextUtils.isEmpty( list.get(3))) {
            map.put("name",  list.get(3));
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.QUERYDUTYRECORDLIST, map));
        new GetTask<GetDutyRecordList>(activity,GetDutyRecordList.class,ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }
}
