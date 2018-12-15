package com.kongtiaoapp.xxhj.mvp.view;

import com.kongtiaoapp.xxhj.mvp.base.BaseView;

/**
 * Created by xxhj_g on 2017/11/22.工单统计
 */

public interface WorkOrderSatisficView<T> extends BaseView {
    void getStasticData(T data);

    void getSucceedPaint(T data);

    void getSecondSucceedPaint(T data);

    void getListNull(T noData);

    /**
     * 对工程师工单统计
     */
    void engineerRecordForm(T data);

    /**
     * 工程师为空的时候统计
     */
    void engineerRecordFormListNull();

    /**
     * 工程师错误统计
     */
    void engineerRecordFormError();

    /**
     * 报修工单
     */
    void repairForm(T data);

    /**
     * 报修工单为空
     */
    void repairFormListNull();

    /**
     * 报修工单错误
     */
    void repairFormError();
}
