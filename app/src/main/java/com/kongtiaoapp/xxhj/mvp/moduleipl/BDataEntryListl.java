package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.BPD_DataEntryBean;
import com.kongtiaoapp.xxhj.mvp.module.BDataEntryListM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by G_XXHJ on 2018/9/14.
 */

public class BDataEntryListl implements BDataEntryListM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotBlank(data.toString())) {
            map.put("projectId", data.toString());
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPOWERDEVICEINFO, map));
        new GetTask<BPD_DataEntryBean>(activity,BPD_DataEntryBean.class, ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }
}
