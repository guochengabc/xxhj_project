package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.GetAboutUs;
import com.kongtiaoapp.xxhj.mvp.module.AboutUsModule;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by xxhj_g on 2017/5/2.   关于我们
 */
public class AboutUsModulelpl implements AboutUsModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("para", ParamJson.map2Json(HttpMethod.GETABOUTUS, map));
        new PostTask<GetAboutUs>(activity,GetAboutUs.class, ConstantValue.HTTP_URLS,builder,true,listener).execute();
    }
}
