package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.Auto_Manual_ParamsBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.Auto_Manual_ParamsModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class Auto_Manual_Paramslpl implements Auto_Manual_ParamsModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", App.sp.getProjectId());
        map.put("currentPage", data + "");
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json_param(HttpMethod.GETRUNNINGMDATA, map));
        new GetTask<Auto_Manual_ParamsBean>(activity, Auto_Manual_ParamsBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), false,listener).execute();
    }

    @Override
    public void setdataNextpage(Activity activity, Object data, ResponseXXHJListener listener) {
        List list= (List) data;
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTRUNNINGMDATA, ((Map<String,String>)list.get(0)), App.sp.getProjectId(), list.get(1).toString(), list.get(2).toString(), list.get(3).toString()));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), false,listener).execute();
    }
}
