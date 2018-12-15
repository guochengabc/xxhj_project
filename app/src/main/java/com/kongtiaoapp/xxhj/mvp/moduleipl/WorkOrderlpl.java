package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.WorkOrderListBean;
import com.kongtiaoapp.xxhj.mvp.module.WorkOrderModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/6/23.
 */

public class WorkOrderlpl implements WorkOrderModule {

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        String method = "";
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> params = new HashMap<String, String>();
        map.put("keyWord", list.get(0));
        map.put("currentPage", list.get(1));
        map.put("pageSize", list.get(3));
        if (list.get(2).equals("2")) {
            method = ParamJson.map2Json(HttpMethod.LISTMYDISPATCHFORM, map);
        } else if (list.get(2).equals("3")) {
            method = ParamJson.map2Json(HttpMethod.LISTUNFINISHHDFORM, map);
        } else {
            if (list.get(2).equals("0") || list.get(2).equals("1")) {
                map.put("dispType", list.get(2));
            }
            method = ParamJson.map2Json(HttpMethod.LISTDISPATCHFORM, map);
        }
        params.put(HttpMethod.KEY, method);
        new GetTask<WorkOrderListBean>(activity, WorkOrderListBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
