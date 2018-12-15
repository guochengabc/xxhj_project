package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.FriendMesgList;
import com.kongtiaoapp.xxhj.bean.MaterialBean;
import com.kongtiaoapp.xxhj.bean.OrderTimeBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.module.EngineerModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by xxhj_g on 2017/7/19.
 */

public class Engineerlpl implements EngineerModule {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dispatchId", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDISPATCHDETAIL, map));
        new GetTask<WorkOrderGet>(activity, WorkOrderGet.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void setOrderTaking(Activity activity, String dispatchId,ResponseXXHJListener listener) {//工程师接单
        Map<String, String> map = new HashMap<String, String>();
        map.put("dispatchId",dispatchId);
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.ACCEPTREPAIRINFO, map));
        new GetTask<OrderTimeBean>(activity, OrderTimeBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void setPros(Activity activity, Object data, Object lists, ResponseXXHJListener listener) {
        List<MaterialBean> listBean = (List<MaterialBean>) lists;
        List<String> list = (List<String>) data;
        // FormBody.Builder builder = new FormBody.Builder();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("dispatchId", list.get(0));
        map.put("orderTime", list.get(1));
        map.put("domain", list.get(2));
        map.put("prspDesc", list.get(3));
        map.put("estimateTime", list.get(4));
        map.put("material", listBean);
        if (list.size()>5){
            for (int i = 5; i < list.size(); i++) {
                File file = new File(list.get(i));
                builder.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
            }
            builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTPROSPECTINFO, map)));
            new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builder, true, true, listener).execute();
        }else{
            FormBody.Builder builders=new FormBody.Builder();
            builders.add("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTPROSPECTINFO, map)));
            new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builders,  true, listener).execute();
        }

    }

    @Override
    public void Commit(Activity activity, Object data, String isFinish, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("dispatchId", list.get(0));
        map.put("isFinish", list.get(1));
        if (isFinish.equals("")) {//没有完成
            map.put("department",list.get(2));
            map.put("finishDesc", list.get(3));
          if (list.size()>4){
              MultipartBody.Builder builder = new MultipartBody.Builder();
              builder.setType(MultipartBody.FORM);
              for (int i = 4; i < list.size(); i++) {
                  File file = new File(list.get(i));
                  builder.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
              }
              builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTFINISHINFO, map)));
              new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builder, true, true, listener).execute();
          }else{
              FormBody.Builder builders=new FormBody.Builder();
              builders.add("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTFINISHINFO, map)));
              new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builders,  true, listener).execute();
          }

        } else {//已完成
            map.put("finishTime", list.get(2));
            map.put("finishDesc", list.get(3));
         if (list.size()>4){
             MultipartBody.Builder builder = new MultipartBody.Builder();
             builder.setType(MultipartBody.FORM);
             for (int i = 4; i < list.size(); i++) {
                 File file = new File(list.get(i));
                 builder.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
             }
             builder.addFormDataPart("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTFINISHINFO, map)));
             new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builder, true, true, listener).execute();
         }else{
             FormBody.Builder builders=new FormBody.Builder();
             builders.add("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json_params(HttpMethod.POSTFINISHINFO, map)));
             new PostTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS, builders,  true, listener).execute();
         }

        }



    }

    @Override
    public void evaluate(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("dispatchId", list.get(0));
        map.put("isQualified", list.get(1));
        map.put("evaluateDesc", list.get(2));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTEVALUATEINFO, map));
        new GetTask<FriendMesgList>(activity, FriendMesgList.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
