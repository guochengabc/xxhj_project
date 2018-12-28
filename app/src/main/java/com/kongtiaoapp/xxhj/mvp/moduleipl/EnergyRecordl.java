package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.util.Log;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.EnergyRecordBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.EnergyRecordM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.Get_NO_Task;
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

/**
 * Created by G_XXHJ on 2018/6/27.  能源计量录入
 */

public class EnergyRecordl implements EnergyRecordM {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("sensorId", list.get(0));
        map.put("recordType", list.get(1));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETFORMENTRYITEM, map));
        new GetTask<EnergyRecordBean>(activity, EnergyRecordBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void cacurlatorData(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("sensorId", "" + list.get(0).trim());
        map.put("name", list.get(1).trim());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETFORMCOMPARAM, map));
        new Get_NO_Task<EnergyRecordBean>(activity, EnergyRecordBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), listener).execute();
    }

    @Override
    public void commitData(Activity activity, Object data, Object data1, Object sensorId, Object recordStatus, ResponseXXHJListener listener) {
        List<EnergyRecordBean.ResobjBean.ElectricityBean> list = (List<EnergyRecordBean.ResobjBean.ElectricityBean>) data;
        ArrayList<ImageItem> selImageList = (ArrayList<ImageItem>) data1;
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
        map.put("sensorId", sensorId.toString());
        map.put("recordStatus", recordStatus.toString());
        map.put("electricity", listC);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i < selImageList.size(); i++) {
            File file = BitmapUtils.getFileComPress(selImageList.get(i).path.toString(), 300, 300);
            builder.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
        }
        builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTENTRYDATA, map)));
        new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builder, true, true, listener).execute();
        Log.i("ffffffffff", "====提交数据====" + Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.SAVEINPUTRECORDDETAILS, map)));
    }

    @Override
    public void getPre(Activity activity, Object sensorId, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sensorId", sensorId.toString());
        map.put("slide", "on");
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETENTRYLISTINFO, map));
        new GetTask<EnergyRecordBean>(activity, EnergyRecordBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getNext(Activity activity, Object sensorId, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sensorId", sensorId.toString());
        map.put("slide", "under");
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETENTRYLISTINFO, map));
        new GetTask<EnergyRecordBean>(activity, EnergyRecordBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

}
