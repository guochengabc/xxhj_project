package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/8/5.
 */

public class WorkOrderListBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"Content":"sfdsfsd","CreateTime":"2017-08-05 16:39:03","DispState":2,"DispStateName":"进行中","DispType":0,"DispatchId":"14a5839f05794988a52017f845bafeff","DispatchNum":"110005","Location":"sdf","RepairType":0,"RepairUserName":"工程师","LimitTime":"2017-08-05 16:55:18","ReportTime":"2017-08-05 16:55:18","ReportUser":"sdf"},{"Content":"灯泡又特么坏了","CreateTime":"2017-08-04 15:56:01","DispState":3,"DispStateName":"异常","DispType":0,"DispatchId":"12656d35e7804764b6c4debf683baf18","DispatchNum":"110004","FinishTime":"2017-08-04 13:06:09","Location":"C2座","RepairType":1,"RepairUserName":"灬达","LimitTime":"2017-08-05 16:55:18","ReportTime":"2017-08-05 16:55:18","ReportUser":"水手"},{"Content":"电梯又特么坏了","CreateTime":"2017-08-04 15:28:36","DispState":1,"DispStateName":"已派单","DispType":0,"DispatchId":"1d2ed96d4d404bcb99cf22beb2ba815b","DispatchNum":"110003","Location":"C1座","RepairType":1,"RepairUserName":"灬达","LimitTime":"2017-08-05 16:55:18","ReportTime":"2017-08-05 16:55:18","ReportUser":"水手"}]
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
         * Content : sfdsfsd
         * CreateTime : 2017-08-05 16:39:03
         * DispState : 2
         * DispStateName : 进行中
         * DispType : 0
         * DispatchId : 14a5839f05794988a52017f845bafeff
         * DispatchNum : 110005
         * Location : sdf
         * RepairType : 0
         * RepairUserName : 工程师
         * LimitTime : 2017-08-05 16:55:18
         * ReportTime : 2017-08-05 16:55:18
         * ReportUser : sdf
         * FinishTime : 2017-08-04 13:06:09
         */

        private String Content;
        private String CreateTime;
        private int DispState;
        private String DispStateName;
        private int DispType;
        private String DispatchId;
        private String DispatchNum;
        private String Location;
        private int RepairType;
        private String RepairUserName;
        private String LimitTime;
        private String ReportTime;
        private String ReportUser;
        private String FinishTime;
        private String EvaluateDesc;
        private String Status;
        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getDispState() {
            return DispState;
        }

        public void setDispState(int DispState) {
            this.DispState = DispState;
        }

        public String getDispStateName() {
            return DispStateName;
        }

        public void setDispStateName(String DispStateName) {
            this.DispStateName = DispStateName;
        }

        public int getDispType() {
            return DispType;
        }

        public void setDispType(int DispType) {
            this.DispType = DispType;
        }

        public String getDispatchId() {
            return DispatchId;
        }

        public void setDispatchId(String DispatchId) {
            this.DispatchId = DispatchId;
        }

        public String getDispatchNum() {
            return DispatchNum;
        }

        public void setDispatchNum(String DispatchNum) {
            this.DispatchNum = DispatchNum;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public int getRepairType() {
            return RepairType;
        }

        public void setRepairType(int RepairType) {
            this.RepairType = RepairType;
        }

        public String getRepairUserName() {
            return RepairUserName;
        }

        public void setRepairUserName(String RepairUserName) {
            this.RepairUserName = RepairUserName;
        }

        public String getLimitTime() {
            return LimitTime;
        }

        public void setLimitTime(String LimitTime) {
            this.LimitTime = LimitTime;
        }

        public String getReportTime() {
            return ReportTime;
        }

        public void setReportTime(String ReportTime) {
            this.ReportTime = ReportTime;
        }

        public String getReportUser() {
            return ReportUser;
        }

        public void setReportUser(String ReportUser) {
            this.ReportUser = ReportUser;
        }

        public String getFinishTime() {
            return FinishTime;
        }

        public void setFinishTime(String FinishTime) {
            this.FinishTime = FinishTime;
        }

        public String getEvaluateDesc() {
            return EvaluateDesc;
        }

        public void setEvaluateDesc(String evaluateDesc) {
            EvaluateDesc = evaluateDesc;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }
    }
}
