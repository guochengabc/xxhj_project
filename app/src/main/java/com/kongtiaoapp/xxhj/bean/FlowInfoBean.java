package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/8/10.
 */

public class FlowInfoBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"CreateTime":"2017-08-10 14:15:57","FlowDesc":"调度员已提交报修单，等待工程师接单。","FlowId":"7dd096a59b0148c4aa7dd193a42e0872","FlowName":"已提交"},{"CreateTime":"2017-08-10 14:16:59","FlowDesc":"工程师已接受报修单，准备现场勘查。","FlowId":"c152ca79ac0242b2b9bec2a63834c43d","FlowName":"已接单"},{"CreateTime":"2017-08-10 14:17:14","FlowDesc":"工程师已完成勘查工作，进行维修。","FlowId":"48f4d7d3cd404ae681621d1d062d08a2","FlowName":"已勘察"},{"CreateTime":"2017-08-10 14:17:28","FlowDesc":"工程师已完成维修工作，等待评价。","FlowId":"f2074bc8f7b6401a8ec4a974c9a88e54","FlowName":"已完成"},{"CreateTime":"2017-08-10 14:17:57","FlowDesc":"质检员评价合格，报修单流程结束。","FlowId":"0d34231efed64a44b3e17a600b73c80d","FlowName":"结束"}]
     */

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * CreateTime : 2017-08-10 14:15:57
         * FlowDesc : 调度员已提交报修单，等待工程师接单。
         * FlowId : 7dd096a59b0148c4aa7dd193a42e0872
         * FlowName : 已提交
         */

        private String CreateTime;
        private String FlowDesc;
        private String FlowId;
        private String FlowName;

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getFlowDesc() {
            return FlowDesc;
        }

        public void setFlowDesc(String FlowDesc) {
            this.FlowDesc = FlowDesc;
        }

        public String getFlowId() {
            return FlowId;
        }

        public void setFlowId(String FlowId) {
            this.FlowId = FlowId;
        }

        public String getFlowName() {
            return FlowName;
        }

        public void setFlowName(String FlowName) {
            this.FlowName = FlowName;
        }
    }
}
