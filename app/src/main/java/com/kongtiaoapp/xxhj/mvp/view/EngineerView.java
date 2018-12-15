package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/8/5.
 */

public interface EngineerView extends BaseView {
    void setDetailInfo(WorkOrderGet bean);
    void setOrderTaking(String orderTime);//接单
    void setCommit();
    void setCommitEvaluate();

}
