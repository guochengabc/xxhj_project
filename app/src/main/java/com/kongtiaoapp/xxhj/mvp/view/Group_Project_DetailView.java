package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/4/28.
 */
public interface Group_Project_DetailView<T> extends BaseView {
    void getCommitList(T data);//获得评论列表
     void postCommit(T data);//提交留言
    void deleteCommitList(T data);//删除评论
    void getPaintAnylize(T data);//图表分析诊断功能
}
