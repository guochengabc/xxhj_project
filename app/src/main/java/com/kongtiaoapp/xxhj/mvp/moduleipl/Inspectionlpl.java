package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.InspectionBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.InspectionModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by G_XXHJ on 2018/4/29.
 */

public class Inspectionlpl implements InspectionModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("deviceId",data.toString());
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETINSPECTIONPARAM, params));
        new GetTask<InspectionBean>(activity, InspectionBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();

    }

    @Override
    public void commitInsp(Activity activity, Object data, ResponseXXHJListener listener) {
        List list= (List) data;
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deviceId",list.get(0).toString());
        map.put("data",list.get(1));
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTDEVICESTATUS, map,""));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms_OBJ(params), true, listener).execute();
    }
}
