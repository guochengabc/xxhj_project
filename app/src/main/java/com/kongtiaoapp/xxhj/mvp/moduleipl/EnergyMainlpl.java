package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.EnergyTopBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.mvp.module.EnergyMainM;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

public class EnergyMainlpl implements EnergyMainM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", data.toString());
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("para", ParamJson.map2Json(HttpMethod.ENERGYMAIN, map));
        new PostTask<EnergyTopBean>(activity, EnergyTopBean.class, ConstantValue.HTTP_URLS, builder, true, listener).execute();
    }

    @Override
    public void getChart(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", list.get(0));
        map.put("sensorId", list.get(1));
        map.put("sensorName", list.get(2));
        map.put("date", list.get(3));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("para", ParamJson.map2Json(HttpMethod.ENERGYCHART, map));
        new PostTask<EnvironmentCPaintBean>(activity, EnvironmentCPaintBean.class, ConstantValue.HTTP_URLS, builder, true, listener).execute();
    }
}
