package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.GetSucceedRecordBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.ZhiBanSubmitModule;
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
public class ZhiBanSubmitlpl implements ZhiBanSubmitModule {
    @Override
    public void submit(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("OffDutyId", App.sp.getUid());
        map.put("SuccessorId", list.get(0));
        if (TextUtils.isEmpty(list.get(1))) {
            map.put("OffDutyMember", "");
        } else {
            map.put("OffDutyMember", list.get(1));
        }
        map.put("BeginTime", list.get(2));
        map.put("EndTime", list.get(3));
        if (TextUtils.isEmpty(list.get(4))) {
            map.put("SuccessorMember", "");
        } else {
            map.put("SuccessorMember", list.get(4));
        }
        map.put("WorkContents", list.get(5));
        map.put("Keyworks", list.get(6));
        map.put("LeaderAssign", list.get(7));
        map.put("UserId", App.sp.getUid());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTDUTYRECORD, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void Commitzhiban(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("DutyId", list.get(0));
        map.put("IsSucceed", list.get(1));
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.SUCCEED, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

        Map<String, String> map = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETOFFDUTYRECORD, map));
        new GetTask<GetSucceedRecordBean>(activity, GetSucceedRecordBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
