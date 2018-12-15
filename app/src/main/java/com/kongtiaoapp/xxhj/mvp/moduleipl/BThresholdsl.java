package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.ParamSettingBean;
import com.kongtiaoapp.xxhj.mvp.module.BThresholdsM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by G_XXHJ on 2018/9/13.  变配电  报警阈值
 */

public class BThresholdsl implements BThresholdsM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("projectId",data.toString());
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPARAMSETINFO, params));
        new GetTask<ParamSettingBean>(activity, ParamSettingBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();

    }
}
