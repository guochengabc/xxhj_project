package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.EngineerRecordFormBean;
import com.kongtiaoapp.xxhj.bean.RepairFormSecondBean;
import com.kongtiaoapp.xxhj.bean.WorkOrderStasficBean;
import com.kongtiaoapp.xxhj.mvp.module.WorkOrderSatisficModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/11/22.
 */

public class WorkOrderSatisficlpl implements WorkOrderSatisficModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        if (list.size() == 2) {
            map.put("dispState", list.get(0));
            map.put("userId", list.get(1));
        } else {
            map.put("date", list.get(0));
            map.put("dispState", list.get(1));
            map.put("userId", list.get(2));
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETWORKSTATISTICS, map));
        new GetTask<WorkOrderStasficBean>(activity, WorkOrderStasficBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getEngineerRecordForm(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        if (list.size() == 2) {
            map.put("dispState", list.get(0));
            map.put("userId", list.get(1));
        } else {
            map.put("date", list.get(0));
            map.put("dispState", list.get(1));
            map.put("userId", list.get(2));
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETENGINEERWORKSTATS, map));
        new GetTask<EngineerRecordFormBean>(activity, EngineerRecordFormBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();

    }

    @Override
    public void getRepairRecordForm(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        if (list.size() == 1) {
            map.put("date", list.get(0));
        } else {
            map.put("date", list.get(0));
            map.put("userId", list.get(1));
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETREPAIRWORKSTATS, map));
        new GetTask<RepairFormSecondBean>(activity, RepairFormSecondBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();

    }
}
