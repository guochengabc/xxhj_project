package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.PersonInfoModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class PersonInfolpl implements PersonInfoModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("UserId", App.sp.getUid());
        if (!TextUtils.isEmpty(list.get(0))) {//头像
            map.put("Avatar", list.get(0));
        }
        if (!TextUtils.isEmpty(list.get(1))) {//昵称
            map.put("UserName", list.get(1));
        }
        if (!TextUtils.isEmpty(list.get(2))) {//性别
            map.put("Gender", list.get(2));
        }
        if (!TextUtils.isEmpty(list.get(3))) {//城市
            map.put("City", list.get(3));
        }
      /*  if (!TextUtils.isEmpty(company)) {//工作单位
            map.put("name", name);
        }*/
        if (!TextUtils.isEmpty(list.get(4))) {//学校
            map.put("School", list.get(4));
        }
        if (!TextUtils.isEmpty(list.get(5))) {//兴趣爱好
            map.put("Interest", list.get(5));
        }
        if (map.size() == 1) {
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json(HttpMethod.POSTUSERINFO, map)));
        new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS
                , builder, true, listener).execute();
    }
}
