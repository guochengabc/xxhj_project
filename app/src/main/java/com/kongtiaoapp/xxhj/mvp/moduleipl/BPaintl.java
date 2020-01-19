package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.BTabViewBean;
import com.kongtiaoapp.xxhj.bean.EtcStatisticBean;
import com.kongtiaoapp.xxhj.bean.ChartDataBean;
import com.kongtiaoapp.xxhj.mvp.module.BPaintM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by G_XXHJ on 2018/10/9.
 */

public class BPaintl implements BPaintM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("date", list.get(0));
        map.put("type", list.get(1));
        map.put("projectId", list.get(2));
        map.put("deviceId",list.get(3));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPOWERMONITORDATA, map));
        new GetTask<ChartDataBean>(activity, ChartDataBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getTabView(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", list.get(0));
        map.put("projectId", list.get(1));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPOWERCHARTDATASOURCES, map));
        new GetTask<BTabViewBean>(activity, BTabViewBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getFGDL(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("date", list.get(0));
        map.put("projectId", list.get(2));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPOWERTABLEDATA, map));
        new GetTask<EtcStatisticBean>(activity, EtcStatisticBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), false, listener).execute();
    }
}
