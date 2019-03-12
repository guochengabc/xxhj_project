package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.mvp.module.EnvironmentInnerModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取环境监测模块
 */
public class EnvironmentInnerlpl implements EnvironmentInnerModule {
    @Override
    public void EnvironmentInnerInfo(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", "6d96a71ce7154dac867b38fb57834a00");
        map.put("deviceId", "");
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETENVIRONMENTDATA, map));
        new GetTask<EnvironmentInnerBan>(activity, EnvironmentInnerBan.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void EnvironmentInnerPaint(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", "6d96a71ce7154dac867b38fb57834a00");
        map.put("date", list.get(0));
        map.put("chartType", list.get(1));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETENVIRONMENTCHART, map));
        new GetTask<EnvironmentCPaintBean>(activity, EnvironmentCPaintBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

    }
}
