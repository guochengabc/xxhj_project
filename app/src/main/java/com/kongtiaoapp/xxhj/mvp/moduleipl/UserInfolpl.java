package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.UserInfoBean;
import com.kongtiaoapp.xxhj.mvp.module.UserInfoModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.Post_NO_Task;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by xxhj_g on 2017/7/5.  用户管理数据模型
 */

public class UserInfolpl implements UserInfoModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json(HttpMethod.LISTUSERINFO)));
        new Post_NO_Task<UserInfoBean>(activity, UserInfoBean.class, ConstantValue.HTTP_URLS, builder, listener).execute();
    }

    @Override
    public void getModifyManager(Activity activity, List<String> list, ResponseXXHJListener listener) {
        Map<String,String> map=new LinkedHashMap<>();
        map.put("toUserId",list.get(0));
        map.put("level",list.get(1));
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_param(HttpMethod.MODIFYAUTHORITY,map)));
        new Post_NO_Task<RBResponse>(activity,RBResponse.class, ConstantValue.HTTP_URLS,builder,listener).execute();
    }
}
