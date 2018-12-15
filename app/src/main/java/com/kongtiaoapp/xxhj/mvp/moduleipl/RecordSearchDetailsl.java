package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.util.Log;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.RecordDetailsBean;
import com.kongtiaoapp.xxhj.mvp.module.RecordSearchDetailsM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.utils.BitmapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RecordSearchDetailsl implements RecordSearchDetailsM {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("sensorId", list.get(0));
        map.put("time", list.get(1));
        map.put("recordTime", list.get(2));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETINPUTRECORDDETAILS, map));
        new GetTask<RecordDetailsBean>(activity, RecordDetailsBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }


    @Override
    public void recallData(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("sensorId", list.get(0));
        map.put("time", list.get(1));
        map.put("recordTime", list.get(2));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.CANCELINPUTRECORD, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void modifyData(Activity activity, Object dataO, Object dataT, Object dataTH, Object dataF, ResponseXXHJListener listener) {
        List<RecordDetailsBean.ResobjBean> list = (List<RecordDetailsBean.ResobjBean>) dataO;
        ArrayList<ImageItem> selImageList = (ArrayList<ImageItem>) dataT;
        List<String> listParam = (List<String>) dataTH;
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map> listC = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            String name = list.get(i).getName();
            String code = list.get(i).getCode();
            Map<String, String> mapC = new LinkedHashMap<>();
            mapC.put("code", code);
            mapC.put("name", name);
            listC.add(mapC);
        }
        map.put("sensorId",dataTH.toString());
        map.put("recordStatus", dataF.toString());
        map.put("electricity", listC);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i < selImageList.size(); i++) {
            File file = BitmapUtils.getFileComPress(selImageList.get(i).path.toString(), 300, 300);
            builder.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
        }
        builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.SAVEINPUTRECORDDETAILS, map)));
           new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builder, true, true, listener).execute();
        Log.i("ffffffffff", "====提交数据====" + Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.SAVEINPUTRECORDDETAILS, map)));

    }
}
