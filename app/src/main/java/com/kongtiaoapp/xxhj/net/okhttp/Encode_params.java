package com.kongtiaoapp.xxhj.net.okhttp;

import android.text.TextUtils;

import com.kongtiaoapp.xxhj.App;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by guoc on 2016/12/24.    YesToken_Encodeparms  带token的请求    NoToken_Encodeparms不带token的请求方法
 */
public class Encode_params {
    public static String NoToken_Encodeparms(Map<String, String> param) {

        StringBuilder buffer = new StringBuilder();
        if (param != null) {
            buffer.append("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    continue;
                }
                try {
                    buffer.append(URLEncoder.encode(key, "UTF-8"));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(value, "UTF-8"));
                    buffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        String str = buffer.toString();
        //去掉最后的&
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static String NoToken_Encodeparms(String param) {

        StringBuilder buffer = new StringBuilder();
        if (param != null) {
            try {
                buffer.append(URLEncoder.encode(param, "UTF-8"));
                buffer.append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        String str = buffer.toString();
        //去掉最后的&
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
        }
        return null;

    }
    public static String NoToken_Encodeparms_OBJ(Map<String, Object> param) {

        StringBuilder buffer = new StringBuilder();
        if (param != null) {
            buffer.append("?");
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    continue;
                }
                try {
                    buffer.append(URLEncoder.encode(key, "UTF-8"));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(value, "UTF-8"));
                    buffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        String str = buffer.toString();
        //去掉最后的&
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static String NoToken_Encodeparms_OBJ(String param) {

        StringBuilder buffer = new StringBuilder();
        if (param != null) {
            try {
                buffer.append(URLEncoder.encode(param, "UTF-8"));
                buffer.append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        String str = buffer.toString();
        //去掉最后的&
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
        }
        return null;

    }
    //带token的get网络请求
    public static String YesToken_Encodeparms(Map<String, String> params) {

        return NoToken_Encodeparms(params) + "&token=" + App.sp.getToken();
    }

    //带token的get网络请求
    public static String YesToken_Encodeparms_OBJ(Map<String, Object> params) {

        return NoToken_Encodeparms_OBJ(params) + "&token=" + App.sp.getToken();
    }
}
