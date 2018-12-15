package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceInfoGetParamAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.AddDeviceModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class AddDevideModuleIpl implements AddDeviceModule {
    @Override
    public void ModifyDv(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", App.sp.getUid());
        map.put("deviceId", data.toString());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDEVICEINFO, map));
        new GetTask<DeviceParam>(activity, DeviceParam.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void AddDv(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", App.sp.getUid());
        map.put("type", data.toString());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDEVICEINPARAM, map));
        new GetTask<DeviceParam>(activity, DeviceParam.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }


    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List list = (List) data;
        Map<String, String> map = new HashMap<>();
        map.put("UserId", App.sp.getUid());
        map.put("DeviceType", list.get(0).toString());
        if ("1".equals(list.get(1).toString())) {
            map.put("DeviceId", list.get(2).toString());
        }
        List<DeviceParam.DeviceParamList> adapterList = ((DeviceInfoGetParamAdapter) list.get(3)).getmList();
        List<DeviceParam.DeviceParamList> deviceParamLists = (List<DeviceParam.DeviceParamList>) list.get(4);
        for (int i = 0; i < deviceParamLists.size(); i++) {
            DeviceParam.DeviceParamList dp = adapterList.get(i);

            if (("Name".equals(dp.getCode()) || (dp.getIsRequire() != null && dp.getIsRequire().equals("1")))
                    && TextUtils.isEmpty(dp.getData())) {
                ToastUtils.showToast(activity, dp.getValue() + activity.getString(R.string.no_empty));
                return;
            }

            String content = dp.getData();

            if (!TextUtils.isEmpty(dp.getFieldType()) && ConstantValue.ENUM.equals(dp.getFieldType())) {
                if (!TextUtils.isEmpty(content)) {
                    //String[] s = content.split(":");
                    //content = s[0];
                    content = dp.getData();
                }
            }
            map.put(deviceParamLists.get(i).getCode(), content);
        }
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTDEVICEINFO, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
