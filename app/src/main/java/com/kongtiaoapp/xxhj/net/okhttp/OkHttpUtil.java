package com.kongtiaoapp.xxhj.net.okhttp;

import okhttp3.OkHttpClient;

/**
 * Created by guoc on 2016/12/24.
 */
public class OkHttpUtil {
    //TODO  添加证书并信任
    public static OkHttpClient getClient() {
        return HttpsUtils_Certificater.getclient();
    }
}
