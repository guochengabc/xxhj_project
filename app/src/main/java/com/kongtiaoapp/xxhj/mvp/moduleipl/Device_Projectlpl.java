package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.DeviceInfoBean;
import com.kongtiaoapp.xxhj.mvp.module.Device_ProjectModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/9.
 */
public class Device_Projectlpl implements Device_ProjectModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        final Map<String, String> map = new HashMap();
        map.put("projectId", App.sp.getProjectId());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETCURRDEVICEINFO, map));
        new GetTask<DeviceInfoBean>(activity, DeviceInfoBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), (boolean)data,listener).execute();
    }
}
