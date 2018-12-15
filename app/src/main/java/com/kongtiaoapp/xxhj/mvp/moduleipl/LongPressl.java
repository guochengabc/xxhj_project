package com.kongtiaoapp.xxhj.mvp.moduleipl;

import android.app.Activity;

import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.ECodeListBean;
import com.kongtiaoapp.xxhj.mvp.module.LongPressM;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.GetTask;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by G_XXHJ on 2018/7/17.  长按识别图中二维码
 */

public class LongPressl implements LongPressM {
    @Override
    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {
        List<String> list= (List<String>) data;
        Map<String, String> map = new HashMap<String, String>();
        map.put("currentPage",list.get(0));
        Map<String, String> params = new HashMap<String, String>();
        if (list.get(1).equals("0")){//暖通空调
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETDEVICEPARA, map));
        }else if (list.get(1).equals("1")){//能源计量
            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.GETSENSORPARAM, map));
        }

        new GetTask<ECodeListBean>(activity, ECodeListBean.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), true, listener).execute();
    }
}
