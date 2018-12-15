package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.BWeekReportDetailBean;
import com.kongtiaoapp.xxhj.mvp.module.BWeekReportDetailM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by G_XXHJ on 2018/9/15.  变配电  周报/月报  详情
 */

public class BWeekReportDetaill implements BWeekReportDetailM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map<String, String> map = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        map.put("currentPage",list.get(0));
        map.put("reportId",list.get(1));
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPOWERREPORTDETAILS, map));
        new GetTask<BWeekReportDetailBean>(activity, BWeekReportDetailBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
