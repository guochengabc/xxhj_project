package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.bean.HVAC_NewProjectDetailBean;
import com.kongtiaoapp.xxhj.bean.HVAC_ProjectDetailBean;
import com.kongtiaoapp.xxhj.bean.PaintAnylizeBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.Group_Project_DetailModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.PostTask;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by xxhj_g on 2017/4/28.   获取项目详情页
 */
public class Group_Project_DetailModuleIpl implements Group_Project_DetailModule {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List list = (List) data;
        Map<String, String> map = new HashMap<>();
        Log.i("TAG","数组长度=="+list.size());
        if (list.size()==2){
            map.put("projectType",list.get(1).toString());
        }
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPROJECTHOMEINFO, map));
        new GetTask<HVAC_ProjectDetailBean>(activity, HVAC_ProjectDetailBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), (Boolean) list.get(0), listener).execute();

    }


    @Override
    public void new_hvav_main(Activity activity, Object data,  ResponseXXHJListener listener) {
        List list = (List) data;
        Map<String, String> map = new HashMap<>();
        Log.i("TAG","数组长度=="+list.size());
        if (list.size()==2){
            map.put("projectType",list.get(1).toString());
        }
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETNEWHVACTESTHOMEINFO, map));
        new GetTask<HVAC_NewProjectDetailBean>(activity, HVAC_NewProjectDetailBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), (Boolean) list.get(0), listener).execute();

    }

    @Override
    public void getCommitList(Activity activity, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "3");
        map.put("projectId", App.sp.getProjectId());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPROJECTCOMMENTLIST, map));
        new GetTask<CommentsGroupBean>(activity, CommentsGroupBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), false, listener).execute();
    }

    @Override
    public void postCommit(Activity activity, String content, String toUid, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", App.sp.getUid());
        map.put("CommentInfo", content);
        map.put("ProjectId", App.sp.getProjectId());
        if (!TextUtils.isEmpty(toUid)) {
            map.put("ToCommentId", toUid);
        }
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTPROJECTCOMMENT, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    @Override
    public void deleteCommitList(Activity activity, String commentId, ResponseXXHJListener listener) {
        //删除列表  cid
        Map<String, String> map = new HashMap<>();
        map.put("cid", commentId);
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.DELETEPROJECTCOMMENT, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }

    //图表分析诊断
    @Override
    public void getPaintAnylize(Activity activity, String category, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", App.sp.getProjectId());
        map.put("category", category);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("para", Encode_params.NoToken_Encodeparms(ParamJson.map2Json(HttpMethod.GETRUNNINGCHARTEVAL, map)));
        new PostTask<PaintAnylizeBean>(activity, PaintAnylizeBean.class, ConstantValue.HTTP_URLS, builder, true, listener).execute();
    }


}
