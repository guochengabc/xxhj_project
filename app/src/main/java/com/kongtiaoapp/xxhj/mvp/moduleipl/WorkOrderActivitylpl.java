package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.LocationAllBean;
import com.kongtiaoapp.xxhj.bean.SystemBean;
import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.module.WorkOrderActivityModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.kongtiaoapp.xxhj.utils.CommonUtil.getString;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public class WorkOrderActivitylpl implements WorkOrderActivityModule {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dispatchId", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDISPATCHDETAIL, map));
        new GetTask<WorkOrderGet>(activity, WorkOrderGet.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getCommit(Activity activity, Object o, Object listSys, int size, ResponseXXHJListener listener) {
        List<String> list = (List<String>) o;
        List<String> listSsyLocation = (List<String>) listSys;
        String custom_company;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", listSsyLocation.get(0));
        map.put("building", listSsyLocation.get(1));
        map.put("storey", listSsyLocation.get(2));
        map.put("installLocation", listSsyLocation.get(3));
        //调度员
        if (TextUtils.isEmpty(list.get(0))) {//这样设计的原因是单位可填可不填
            custom_company = "";
        } else {
            custom_company = list.get(0);
        }
        if (TextUtils.isEmpty(list.get(1))) {
            ToastUtils.showToast(activity, activity.getString(R.string.repair_content));
            return;
        }
        if (TextUtils.isEmpty(list.get(2))) {
            ToastUtils.showToast(activity, getString(R.string.floor_room));
            return;
        }
        if (TextUtils.isEmpty(list.get(3))) {
            ToastUtils.showToast(activity, getString(R.string.repair_people));
            return;
        }
        if (TextUtils.isEmpty(list.get(4))) {
            ToastUtils.showToast(activity, getString(R.string.connect_phone));
            return;
        }
        if (TextUtils.isEmpty(list.get(5))) {
            ToastUtils.showToast(activity, getString(R.string.work_mode));
            return;
        }
        if (list.get(5).equals("1")) {
            if (TextUtils.isEmpty(list.get(6))) {
                ToastUtils.showToast(activity, getString(R.string.receiver_people));
                return;
            }
        }
        if (TextUtils.isEmpty(list.get(7))) {
            ToastUtils.showToast(activity, getString(R.string.finish_time));
            return;
        }
        map.put("company", custom_company);
        map.put("content", list.get(1));
        map.put("location", list.get(2));
        map.put("reportUser", list.get(3));
        map.put("phone", list.get(4));
        map.put("dispPattern", list.get(5));
        map.put("repairUserId", list.get(6));
        map.put("reportTime", list.get(7));
        if (list.size() - size == 9) {
            if (!list.get(8).equals("")) {
                map.put("dispatchId", list.get(8));
            }
        }
        if (size == 0) {
            Map<String, String> params = new HashMap<String, String>();

            if (list.size() - size == 9) {
                params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.UPDATEREPAIRINFO, map, ""));
            } else {
                params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTREPAIRINFO, map, ""));
            }
            new GetTask<DeviceParam>(activity, DeviceParam.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();

        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (int i = list.size() - size; i < list.size(); i++) {
                File file = new File(list.get(i));
                builder.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
            }
            if (list.size() - size == 9) {
                builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.UPDATEREPAIRINFO, map)));
            } else {
                builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTREPAIRINFO, map)));
            }

            new PostTask<DeviceParam>(activity, DeviceParam.class, ConstantValue.HTTP_URLS, builder, true, true, listener).execute();
        }
    }

    @Override
    public void getRepairStatus(Activity activity, String param, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dispatchId", param);
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDFORMSTATE, map));
        new GetTask<WorkOrderGet>(activity, WorkOrderGet.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getSystem(Activity activity, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", App.sp.getUid());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.SYSTEM, map));
        new GetTask<SystemBean>(activity, SystemBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getLocation(Activity activity, String whichType, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", App.sp.getUid());
        map.put("whichType", whichType);
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.LOCATION, map));
        new GetTask<LocationAllBean>(activity, LocationAllBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

}
