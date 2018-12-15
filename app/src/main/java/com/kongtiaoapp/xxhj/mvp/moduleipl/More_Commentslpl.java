package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;
import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.module.More_CommentsModule;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxhj_g on 2017/5/4.
 */
public class More_Commentslpl implements More_CommentsModule {
    @Override
    public void commit_Comments(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map<String, String> map = new HashMap<>();
        map.put("UserId", App.sp.getUid());
        map.put("CommentInfo", list.get(0));
        map.put("ProjectId", App.sp.getProjectId());
        if (!TextUtils.isEmpty(list.get(1))) {
            map.put("ToCommentId", list.get(1));
        }
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.POSTPROJECTCOMMENT, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }

    @Override
    public void delete_Comments(Activity activity, Object data, ResponseXXHJListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", data.toString());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.DELETEPROJECTCOMMENT, map));
        new GetTask<RBResponse>(activity, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }

    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List list= (List) data;
        boolean isRefresh= (boolean) list.get(0);
          int currentPage= (int) list.get(1);
        Map<String, String> map = new HashMap<>();
        if (isRefresh == true) {
            map.put("currentPage", String.valueOf(1));
            map.put("pageSize", String.valueOf(currentPage * 10));
        } else {
            map.put("currentPage", String.valueOf(currentPage));
        }
        map.put("projectId", App.sp.getProjectId());
        Map<String, String> params = new HashMap<>();
        params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETPROJECTCOMMENTLIST, map));
        new GetTask<CommentsGroupBean>(activity, CommentsGroupBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true,listener).execute();
    }
}
