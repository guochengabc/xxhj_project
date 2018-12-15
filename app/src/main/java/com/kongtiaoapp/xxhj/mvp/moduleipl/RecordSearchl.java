package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RecordSearchListBean;
import com.kongtiaoapp.xxhj.mvp.module.RecordSearchM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 录表查询
 */
public class RecordSearchl implements RecordSearchM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        /*  list_param.add(String.valueOf(currentPage));
        list_param.add(recordType);
        list_param.add(start_time);
        list_param.add(end_time);
        list_param.add(txt_please.getText().toString().trim());//人名*/
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
       // map.put("currentPage", list.get(0));
        map.put("requestType", list.get(1));
        map.put("startDate", list.get(2));
        map.put("endDate", list.get(3));
        map.put("userName", list.get(4));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETINPUTRECORD, map));
        new GetTask<RecordSearchListBean>(activity, RecordSearchListBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
