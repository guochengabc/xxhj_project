package com.kongtiaoapp.xxhj.mvp.module;

import android.app.Activity;

import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * Created by xxhj_g on 2017/4/28.
 */
public interface Group_Project_DetailModule<T>  extends BaseModule{
    void new_hvav_main(Activity activity, T data, ResponseXXHJListener listener);//获取暖通新版项目详情首页
    void getCommitList(Activity activity, ResponseXXHJListener listener);
    void postCommit(Activity activity, String content, String toUid, ResponseXXHJListener listener);//提交留言
    void deleteCommitList(Activity activity, String command, ResponseXXHJListener listener);//删除讨论内容
    void getPaintAnylize(Activity activity, String category, ResponseXXHJListener listener);//获取图表分析诊断
}
