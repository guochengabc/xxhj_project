package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.ChangeTokenBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RunningDevice;
import com.kongtiaoapp.xxhj.mvp.module.RunningDeviceListModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class RunningDeviceListlpl implements RunningDeviceListModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("projectId", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETRUNNINGDEVICELIST, map));
        new GetTask<RunningDevice>(activity,RunningDevice.class,ConstantValue.HTTP_URLS+Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }

    @Override
    public void changeToken(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("projectId", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.UPDATETOKEN, map));
        new GetTask<ChangeTokenBean>(activity, ChangeTokenBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }

    @Override
    public void submit_device(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, data.toString());
        new GetTask<RBResponse>(activity,RBResponse.class,ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }
}
