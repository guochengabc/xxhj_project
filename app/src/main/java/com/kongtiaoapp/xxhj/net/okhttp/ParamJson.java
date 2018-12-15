package com.kongtiaoapp.xxhj.net.okhttp;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by xxhj_g on 2017/4/28.
 */
public class ParamJson {
    private static Gson gson = new Gson();

    public static String map2Json(String method, Map<String, String> map) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + gson.toJson(map).toString() + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }
    public static String map2Json(String method, String param) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + param + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }
    public static String map2Json(String method, Map<String, Object> map,String no) {//no没有任何实际意义

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + gson.toJson(map).toString() + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    public static String map2Json(String method) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }
    //修改排序功能设备最新
    public static String map2JsonReal(String method, String deviceId, String data) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "DeviceId" + "\"" + ":" + deviceId + "," + "\"" + "Data" + "\"" + ":" + data + "}" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    //修改排序功能设备
    public static String map2Json(String method, String deviceId, String data) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "DeviceId" + "\"" + ":" + gson.toJson(deviceId) + "," + "\"" + "Data" + "\"" + ":" + data + "}" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    //修改排序功能系统
    public static String map2JsonSys(String method, String projectId, String data) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "ProjectId" + "\"" + ":" + gson.toJson(projectId) + "," + "\"" + "Data" + "\"" + ":" + data + "}" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    public static String map2Jsonp(String method, String projectId, String data) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "ProjectId" + "\"" + ":" + gson.toJson(projectId) + "," + "\"" + "Data" + "\"" + ":" + data + "}" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    public static String map2JsonEvalue(String method, String projectId) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "projectId" + "\"" + ":" + gson.toJson(projectId) + "}" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    /*参数录入时要用*/
    public static String map2Json(String method, Map<String, String> map, String projectId, String deviceId, String deviceType, String IsLast) {
        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "ProjectId" + "\"" + ":" + gson.toJson(projectId) + "," + "\"" + "DeviceId" + "\"" + ":" + deviceId + "," + "\"" + "DeviceType" + "\"" + ":" + deviceType + "," + "\"" + "Data" + "\"" + ":" + gson.toJson(map).toString() + "," + "\"" + "IsLast" + "\"" + ":" + IsLast + "}" + "}";
        return string;
    }

    /*参数录入时要用*/
    public static String map2Json_param(String method, Map<String, String> map) {
        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + gson.toJson(map).toString() + "}";
        return string;
    }

    public static String map2Json_params(String method, Map<String, Object> map) {
        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + gson.toJson(map).toString() + "}";
        return string;
    }
}
