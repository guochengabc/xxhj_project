package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.List;

/**
 * Created by xxhj_g on 2017/7/11.
 */

public interface Add_AUTO_DeviceRunningModule extends BaseModule {
    void setDataForServiceSys(Activity activity, String data, ResponseXXHJListener listener);//获取运行系统记录
    void saveDrag_CommitData(Activity activity, List list, ResponseXXHJListener listener);//保存排序设备
    void saveDrag_CommitDataSys(Activity activity, List list, ResponseXXHJListener listener);//保存排序系统
    void showPaint(Activity activity, List<String> list, ResponseXXHJListener listener);//展示图表
    void onResumeDevice(Activity activity, List<String> list, ResponseXXHJListener listener);//展示图表

}
