package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.Energy8List;
import com.kongtiaoapp.xxhj.mvp.module.Energy8SpecialModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/13.
 */
public class Energy8Speciallpl implements Energy8SpecialModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List list= (List) data;
        Map<String, String> map = new HashMap<>();
        map.put("uid", App.sp.getUid());
        map.put("currentPage",list.get(0).toString());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETSPECIALINFOLIST, map));
        new GetTask<Energy8List>(activity,Energy8List.class,ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),(boolean)list.get(1),listener).execute();
    }
}
