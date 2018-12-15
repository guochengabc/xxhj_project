package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.Report_Running_RecordBean;
import com.kongtiaoapp.xxhj.mvp.module.ReportWorkModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/8/26.导出工单记录
 */

public class ReportWorklpl implements ReportWorkModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("startDate", list.get(0));
        map.put("endDate", list.get(1));
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.EXPDISPATCHFORM, map));
        new GetTask<Report_Running_RecordBean>(activity,Report_Running_RecordBean.class, ConstantValue.HTTP_URLS+ Encode_params.YesToken_Encodeparms(params),true,listener).execute();
    }
    @Override
    public void downDocument(Object data,Callback.ProgressCallback listener) {
        List<String> list = (List<String>) data;
        RequestParams params = new RequestParams(list.get(0));
        //   params.setSslSocketFactory(HttpsUtils_Certificater.getSslSocketFactory());
        params.setConnectTimeout(18000);
        params.setSaveFilePath(list.get(1));
        x.http().get(params, listener);
    }
}
