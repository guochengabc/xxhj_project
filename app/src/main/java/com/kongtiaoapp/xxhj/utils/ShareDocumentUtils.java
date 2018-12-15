package com.kongtiaoapp.xxhj.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxhj_g on 2016/12/8.
 */
public class ShareDocumentUtils {
    //获取单个信息列表
    public static void shareMemo(Context context, String path,String type) {
        Uri uri = Uri.parse(path);
        Intent it = new Intent(Intent.ACTION_SEND);
        it.putExtra(Intent.EXTRA_STREAM, uri);
        it.setType(type);
        context.startActivity(Intent.createChooser(it, "文件发送给"));
    }
    //获取信息列表的摸个信息
    public static List<ResolveInfo> getShareApps(Context context,String type) {
        List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType(type);
        PackageManager pManager = context.getPackageManager();
        mApps = pManager.queryIntentActivities(intent,
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return mApps;
    }
}
