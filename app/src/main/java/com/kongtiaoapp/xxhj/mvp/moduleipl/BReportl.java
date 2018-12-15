package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.GetRDateParam_RecorderBean;
import com.kongtiaoapp.xxhj.bean.ProjectList;
import com.kongtiaoapp.xxhj.bean.Project_Module_Name;
import com.kongtiaoapp.xxhj.bean.Report_Running_RecordBean;
import com.kongtiaoapp.xxhj.mvp.module.BReportM;
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
 * Created by G_XXHJ on 2018/9/13.  变配电  报表导出
 */

public class BReportl implements BReportM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        Map map = new HashMap();
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETRDATADEFAULTPARAM, map));
        new GetTask<GetRDateParam_RecorderBean>(activity, GetRDateParam_RecorderBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getProjectName(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("currentPage", data.toString());
        map.put("pageSize", String.valueOf(30));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPROJECTINFOLIST, map));
        new GetTask<ProjectList>(activity, ProjectList.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void getProjectModule(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("projectId", data.toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPROJECTTEMPLATE, map));
        new GetTask<Project_Module_Name>(activity, Project_Module_Name.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void report_data_forservice(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list = (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("startDate", list.get(0));
        map.put("endDate", list.get(1));
        map.put("templateId", list.get(2));
        map.put("projectId", list.get(3));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.EXRUNNINGDATA, map));
        new GetTask<Report_Running_RecordBean>(activity, Report_Running_RecordBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void downDocument(Object data,Callback.ProgressCallback listener) {
        List<String> list = (List<String>) data;
        RequestParams params = new RequestParams(list.get(0));
        //   params.setSslSocketFactory(HttpsUtils_Certificater.getSslSocketFactory());
        params.setConnectTimeout(18000);
        params.setSaveFilePath(list.get(1));
        x.http().post(params, listener);
    }
}
