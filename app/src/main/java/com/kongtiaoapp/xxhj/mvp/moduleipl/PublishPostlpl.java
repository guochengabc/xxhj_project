package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.PublishPostModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by xxhj_g on 2017/5/5.
 */
public class PublishPostlpl implements PublishPostModule {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

    }

    @Override
    public void setUploadPictrue(Activity activity, List<String> list,ResponseXXHJListener listener) {
       // FormBody.Builder builder = new FormBody.Builder();
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("uid", App.sp.getUid());
        map.put("title", list.get(0));
        map.put("content", list.get(1));
        for (int i = 2; i < list.size(); i++) {
            File file=new File(list.get(i));
            builder.addFormDataPart("image",file.getName(),RequestBody.create(MEDIA_TYPE_PNG,file));
        }
        builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_param(HttpMethod.PUBLISHDISCUSSTHEME, map)));
        new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builder, true, true,listener).execute();
    }
}

