package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.ModuleBean;
import com.kongtiaoapp.xxhj.bean.VersionBean;
import com.kongtiaoapp.xxhj.mvp.module.MainModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.Get_NO_Task;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/4/25.   检查版本更新
 */
public class MainModuleIpl implements MainModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETLATESYSVER, map));
        new Get_NO_Task<VersionBean>(activity, VersionBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), listener).execute();
    }

    @Override
    public void getModule(Activity activity, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.COMMONMENUITEM, map));
        new Get_NO_Task<ModuleBean>(activity, ModuleBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), listener).execute();
    }
}
