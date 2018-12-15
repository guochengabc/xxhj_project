package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.WeibaoBean;
import com.kongtiaoapp.xxhj.mvp.module.AddWBRecordModule;
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
public class AddWBRecordlpl implements AddWBRecordModule{
    @Override
    public void save(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List) data;
        Map<String, String> map = new HashMap<>();
        map.put("UserId", App.sp.getUid());
        map.put("DeviceId", list.get(0));
        if (!TextUtils.isEmpty(list.get(1))) {
            map.put("MaintenanceRecordId", list.get(1));
        }
        map.put("Time", list.get(2));
        map.put("Technician", list.get(3));
        map.put("Content", list.get(4));
        if (!TextUtils.isEmpty(list.get(5))) {
            map.put("Company", list.get(5));
        }
        if (!TextUtils.isEmpty(list.get(6))) {
            map.put("Phone", list.get(6));
        }
        if (!TextUtils.isEmpty(list.get(7))) {
            map.put("Cost", list.get(7));
        }

        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTMTRECORD, map));
        new GetTask<WeibaoBean>(activity,WeibaoBean.class,ConstantValue.HTTP_URLS+Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", App.sp.getUid());
        map.put("recordId", data.toString());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETMTRECORD, map));
        new GetTask<WeibaoBean>(activity,WeibaoBean.class,ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }

}
