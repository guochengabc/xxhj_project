package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.GetGroupProjectListBean;
import com.kongtiaoapp.xxhj.mvp.module.Group_SurveyModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/3.
 */
public class Group_Surveylpl implements Group_SurveyModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map map = new HashMap();
        if (!list.get(0).equals("")) {
            map.put("sortType", list.get(0));
        }
        if (!list.get(1).equals("")) {
            map.put("sortParam", list.get(1));
        }
        if (!list.get(2).equals("")) {
            map.put("filter", list.get(2));
        }
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETGROUPPROJECTLIST, map));
        new GetTask<GetGroupProjectListBean>(activity, GetGroupProjectListBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }
}
