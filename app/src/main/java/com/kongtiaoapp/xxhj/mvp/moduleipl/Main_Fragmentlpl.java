package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.HomeRunningInfoBean;
import com.kongtiaoapp.xxhj.mvp.module.Main_FragmentModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.Get_NO_Task;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/6/20.
 */
public class Main_Fragmentlpl implements Main_FragmentModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETRUNNINGHOMEINFO, map));
        new Get_NO_Task<HomeRunningInfoBean>(activity,HomeRunningInfoBean.class, ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),listener).execute();
    }
}
