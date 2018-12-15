package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.ChangeShiftBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.ChangeShiftsModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by xxhj_g on 2017/11/4.
 */

public class ChangeShiftslpl implements ChangeShiftsModule {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    /**
     * 提交交接班
     */
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        /**后台还没有做好*/
        List list = (List) data;
        Map<String, Object> map = new HashMap<String, Object>();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        map.put("deviceId", list.get(0) + "");
        map.put("mark", list.get(1) + "");
        File file = new File(list.get(2).toString());
        builder.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
        builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.SAVESIFNATUREINFO, map)));
        new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builder, true, true, listener).execute();
    }

    @Override
    public void getCST(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETCSTYPE, map));
        new GetTask<ChangeShiftBean>(activity, ChangeShiftBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
