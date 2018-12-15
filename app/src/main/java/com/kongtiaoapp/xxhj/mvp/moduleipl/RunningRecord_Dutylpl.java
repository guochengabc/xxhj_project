package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RunningRecord_DutyBean;
import com.kongtiaoapp.xxhj.mvp.module.RunningRecord_DutyModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/18.
 */
public class RunningRecord_Dutylpl implements RunningRecord_DutyModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        int size = list.size();
        final Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", App.sp.getProjectId());
        map.put("currentPage", list.get(0));

        if (size == 5) {
            if (!TextUtils.isEmpty(list.get(2))) {
                map.put("startTime", list.get(2));
            }
            if (!TextUtils.isEmpty(list.get(3))) {
                map.put("endTime", list.get(3));
            }
            if (!TextUtils.isEmpty(list.get(4))) {
                map.put("userId", list.get(4));
            }
        } else if (size == 6) {
            map.put("last", list.get(2));
            if (!TextUtils.isEmpty(list.get(3))) {
                map.put("startTime", list.get(3));
            }
            if (!TextUtils.isEmpty(list.get(4))) {
                map.put("endTime", list.get(4));
            }
            if (!TextUtils.isEmpty(list.get(5))) {
                map.put("userId", list.get(5));
            }
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.QUERYRUNNINGDATA, map));
        new GetTask<RunningRecord_DutyBean>(activity, RunningRecord_DutyBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), false, listener).

                execute();
    }
}
