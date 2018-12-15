package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.util.Log;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.HVACPieChartBean;
import com.kongtiaoapp.xxhj.bean.Loading_RefrigeratorBean;
import com.kongtiaoapp.xxhj.mvp.module.ParamPaintM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by G_XXHJ on 2018/5/25.
 */

public class ParamPaintl implements ParamPaintM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("projectId", App.sp.getProjectId());
        Log.i("ffffffffffffff","projectID==Protect_Paintlpl="+App.sp.getProjectId());
        map.put("type", list.get(0));
        map.put("date", list.get(1));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETMONITORDATA, map));
        new GetTask<Loading_RefrigeratorBean>(activity, Loading_RefrigeratorBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getPieChart(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("projectId", App.sp.getProjectId());
        Log.i("ffffffffffffff","projectID==Protect_Paintlpl="+App.sp.getProjectId());
        map.put("type", list.get(0));
        map.put("date", list.get(1));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETCHARTDATASOURCES, map));
        new GetTask<HVACPieChartBean>(activity, HVACPieChartBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getExplan(Activity activity, Object data, ResponseXXHJListener listener) {

    }
}
