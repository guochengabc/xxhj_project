package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.Add_AUDO_DEVICE_PaintBean;
import com.kongtiaoapp.xxhj.bean.Add_AUTO_DeviceBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.mvp.module.Add_AUTO_DeviceRunningModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask_ChangeParam;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/7/11.
 */

public class Add_AUTO_DeviceRunninglpl implements Add_AUTO_DeviceRunningModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {//进入界面正常请求
        Map<String, String> map = new HashMap<>();
        map.put("uid", App.sp.getUid());
        map.put("deviceId", data.toString());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETRUNNINGDATA, map));
        new GetTask_ChangeParam<RunningParam>(activity, RunningParam[].class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();

    }

    //修改排序设备
    @Override
    public void setDataForServiceSys(Activity activity, String data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", App.sp.getUid());
        map.put("projectId", data.toString());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETRUNNINGSYSDATA, map));
        new GetTask_ChangeParam<RunningParam>(activity, RunningParam[].class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    //修改排序系统
    @Override
    public void saveDrag_CommitData(Activity activity, List list, ResponseXXHJListener listener) {//保存排序
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2JsonReal(HttpMethod.POSTRUNNINGORDERPARAM, list.get(0).toString(), list.get(1).toString()));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void saveDrag_CommitDataSys(Activity activity, List list, ResponseXXHJListener listener) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2JsonSys(HttpMethod.POSTRUNSYSORDERPARAM, list.get(0).toString(), list.get(1).toString()));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void showPaint(Activity activity, List<String> list, ResponseXXHJListener listener) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("deviceId", list.get(0));
        params.put("code", list.get(1));
        params.put("date", list.get(2));
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDEVICEMDATA, params));
        new GetTask<Add_AUTO_DeviceBean>(activity, Add_AUTO_DeviceBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void onResumeDevice(Activity activity, List<String> list, ResponseXXHJListener listener) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("deviceId", list.get(0));
        if (!list.get(1).equals("")) {
            params.put("date", list.get(1));
        }
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDEVICEECDATA, params));
        new GetTask<Add_AUDO_DEVICE_PaintBean>(activity, Add_AUDO_DEVICE_PaintBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();

    }
}
