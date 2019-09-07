package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.util.Log;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.bean.NoRealTimeBean;
import com.kongtiaoapp.xxhj.mvp.module.NoRealTimeModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoRealTimelpl implements NoRealTimeModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        if (data.toString().equals("3346a6ab81b64621aaaa83c670f3105b")) {//明德医院
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.MINGDE, map));
        } else {
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.HVAC_MAIN, map));
        }
        new GetTask<EnvironmentInnerBan>(activity, EnvironmentInnerBan.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getChart(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", list.get(0));
        map.put("date", list.get(1));
        map.put("type", list.get(2));
        map.put("position", list.get(3));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.MINGDECHART, map));
        new GetTask<NoRealTimeBean>(activity, NoRealTimeBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getChartMonth(Activity activity, Object data, ResponseXXHJListener lisenter) {
        Log.i("fffffffff","===================");
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", list.get(0));
        map.put("date", list.get(1));
        map.put("type", list.get(2));
        map.put("position", list.get(3));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.MINGDECHART, map));
        new GetTask<NoRealTimeBean>(activity, NoRealTimeBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, lisenter).execute();
    }
}
