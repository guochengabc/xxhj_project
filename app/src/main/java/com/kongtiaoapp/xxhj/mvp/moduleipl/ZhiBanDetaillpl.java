package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.GetZhiBanInfo;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.ZhiBanDetailModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class ZhiBanDetaillpl implements ZhiBanDetailModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("dutyId", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDUTYRECORD, map));
        new GetTask<GetZhiBanInfo>(activity, GetZhiBanInfo.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    //获取最近交班信息
    @Override
    public void getLatePassWork(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("dutyType", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETLATELYDUTYRECORD, map));
        new GetTask<GetZhiBanInfo>(activity, GetZhiBanInfo.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), false, listener).execute();
    }

    //获取最近接班信息
    @Override
    public void getLateTakeWork(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("dutyType", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETLATELYDUTYRECORD, map));
        new GetTask<GetZhiBanInfo>(activity, GetZhiBanInfo.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), false, listener).execute();
    }

    @Override
    public void submit(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("dutyId", list.get(0));
        map.put("dutyId", list.get(0));
        map.put("OffDutyId", App.sp.getUid());
        if (TextUtils.isEmpty(list.get(1))) {
            map.put("OffDutyMember", "");
        } else {
            map.put("OffDutyMember", list.get(1));
        }
        map.put("SuccessorId", list.get(2));
        if (TextUtils.isEmpty(list.get(3))) {
            map.put("SuccessorMember", "");
        } else {
            map.put("SuccessorMember", list.get(3));
        }
        map.put("BeginTime", list.get(4));
        map.put("EndTime", list.get(5));
        map.put("Keyworks", list.get(6));
        map.put("WorkContents", list.get(7));
        map.put("LeaderAssign", list.get(8));
        map.put("DutyStatus", list.get(9));
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.UPDATEDUTYRECORD, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }


}
