package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/8/5.
 */

public class WorkOrderGet extends RBResponse {

    /**
     * code : 40000
     * resobj : {"CanFinish":"","Company":"","Content":"灯泡又特么坏了","DispState":"3","DispType":"0","DispatchId":"0abb380330dc46b6a07b1ab0b400605f","DispatchNum":"110005","DispPattern":"0","Domain":"空调","EstimateTime":"2017-08-04 13:08:18","EvalPhotoArr":["images/work/2017/08/04/6eddf3390782429ea7a650531a7acca7.jpg","images/work/2017/08/04/097b13a02df3402ba10eb81829f1492f.jpg"],"EvalUserId":"2a2ff5e9fe0d4dada0b9b2c9aa6a1e9b","EvalUserName":"灬达","EvaluateDesc":"修的啥玩意啊","FinishDesc":"小溪终于可以汇聚了","FinishPhotoArr":["images/work/2017/08/04/1c5488d33ec6487890313cc9da4fcaa7.jpg"],"FinishTime":"2017-08-04 13:06:09","IsFinish":"1","Department":"维修部门","IsQualified":"0","LimitTime":"2017-08-05 16:55:18","ReportTime":"2017-08-05 16:55:18","Location":"C2座","MaterialArr":[{"Amount":"2","Name":"灯泡","Price":"10","Spec":"15w"},{"Amount":"2","Name":"灯泡","Price":"10","Spec":"10w"}],"OrderTime":"2017-08-04 13:06:09","Phone":"123456789","PrspDesc":"必须能修好","PrspPhotoArr":["images/work/2017/08/04/b909eb766192488796fabe0e8b0e9e50.jpg"],"RepairType":"1","RepairUserId":"2a2ff5e9fe0d4dada0b9b2c9aa6a1e9b","RepairUserName":"灬达","ReportUser":"水手"}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * CanFinish :
         * Company :
         * Content : 灯泡又特么坏了
         * DispState : 3
         * DispType : 0
         * DispatchId : 0abb380330dc46b6a07b1ab0b400605f
         * DispatchNum : 110005
         * DispPattern : 0
         * Domain : 空调
         * EstimateTime : 2017-08-04 13:08:18
         * EvalPhotoArr : ["images/work/2017/08/04/6eddf3390782429ea7a650531a7acca7.jpg","images/work/2017/08/04/097b13a02df3402ba10eb81829f1492f.jpg"]
         * EvalUserId : 2a2ff5e9fe0d4dada0b9b2c9aa6a1e9b
         * EvalUserName : 灬达
         * EvaluateDesc : 修的啥玩意啊
         * FinishDesc : 小溪终于可以汇聚了
         * FinishPhotoArr : ["images/work/2017/08/04/1c5488d33ec6487890313cc9da4fcaa7.jpg"]
         * FinishTime : 2017-08-04 13:06:09
         * IsFinish : 1
         * Department : 维修部门
         * IsQualified : 0
         * LimitTime : 2017-08-05 16:55:18
         * ReportTime : 2017-08-05 16:55:18
         * Location : C2座
         * MaterialArr : [{"Amount":"2","Name":"灯泡","Price":"10","Spec":"15w"},{"Amount":"2","Name":"灯泡","Price":"10","Spec":"10w"}]
         * OrderTime : 2017-08-04 13:06:09
         * Phone : 123456789
         * PrspDesc : 必须能修好
         * PrspPhotoArr : ["images/work/2017/08/04/b909eb766192488796fabe0e8b0e9e50.jpg"]
         * RepairType : 1
         * RepairUserId : 2a2ff5e9fe0d4dada0b9b2c9aa6a1e9b
         * RepairUserName : 灬达
         * ReportUser : 水手
         */

        private String CanFinish;
        private String Company;
        private String Content;
        private String DispState;
        private String DispType;
        private String DispatchId;
        private String DispatchNum;
        private String DispPattern;
        private String Domain;
        private String EstimateTime;
        private String EvalUserId;
        private String EvalUserName;
        private String EvaluateDesc;
        private String FinishDesc;
        private String FinishTime;
        private String IsFinish;
        private String Department;
        private String IsQualified;
        private String LimitTime;
        private String ReportTime;
        private String ProjectId;
        private String ProjectName;
        private String Building;
        private String Storey;
        private String InstallLocation;
        private String Location;
        private String OrderTime;
        private String Phone;
        private String PrspDesc;
        private String RepairType;
        private String RepairUserId;
        private String RepairUserName;
        private String ReportUser;
        private List<String> EvalPhotoArr;
        private List<String> FinishPhotoArr;
        private List<MaterialArrBean> MaterialArr;
        private List<String> PrspPhotoArr;

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String projectId) {
            ProjectId = projectId;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String projectName) {
            ProjectName = projectName;
        }

        public String getBuilding() {
            return Building;
        }

        public void setBuilding(String building) {
            Building = building;
        }

        public String getStorey() {
            return Storey;
        }

        public void setStorey(String storey) {
            Storey = storey;
        }

        public String getInstallLocation() {
            return InstallLocation;
        }

        public void setInstallLocation(String installLocation) {
            InstallLocation = installLocation;
        }

        public String getCanFinish() {
            return CanFinish;
        }

        public void setCanFinish(String CanFinish) {
            this.CanFinish = CanFinish;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getDispState() {
            return DispState;
        }

        public void setDispState(String DispState) {
            this.DispState = DispState;
        }

        public String getDispType() {
            return DispType;
        }

        public void setDispType(String DispType) {
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

        public String getDispPattern() {
            return DispPattern;
        }

        public void setDispPattern(String DispPattern) {
            this.DispPattern = DispPattern;
        }

        public String getDomain() {
            return Domain;
        }

        public void setDomain(String Domain) {
            this.Domain = Domain;
        }

        public String getEstimateTime() {
            return EstimateTime;
        }

        public void setEstimateTime(String EstimateTime) {
            this.EstimateTime = EstimateTime;
        }

        public String getEvalUserId() {
            return EvalUserId;
        }

        public void setEvalUserId(String EvalUserId) {
            this.EvalUserId = EvalUserId;
        }

        public String getEvalUserName() {
            return EvalUserName;
        }

        public void setEvalUserName(String EvalUserName) {
            this.EvalUserName = EvalUserName;
        }

        public String getEvaluateDesc() {
            return EvaluateDesc;
        }

        public void setEvaluateDesc(String EvaluateDesc) {
            this.EvaluateDesc = EvaluateDesc;
        }

        public String getFinishDesc() {
            return FinishDesc;
        }

        public void setFinishDesc(String FinishDesc) {
            this.FinishDesc = FinishDesc;
        }

        public String getFinishTime() {
            return FinishTime;
        }

        public void setFinishTime(String FinishTime) {
            this.FinishTime = FinishTime;
        }

        public String getIsFinish() {
            return IsFinish;
        }

        public void setIsFinish(String IsFinish) {
            this.IsFinish = IsFinish;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String Department) {
            this.Department = Department;
        }

        public String getIsQualified() {
            return IsQualified;
        }

        public void setIsQualified(String IsQualified) {
            this.IsQualified = IsQualified;
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

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public String getOrderTime() {
            return OrderTime;
        }

        public void setOrderTime(String OrderTime) {
            this.OrderTime = OrderTime;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getPrspDesc() {
            return PrspDesc;
        }

        public void setPrspDesc(String PrspDesc) {
            this.PrspDesc = PrspDesc;
        }

        public String getRepairType() {
            return RepairType;
        }

        public void setRepairType(String RepairType) {
            this.RepairType = RepairType;
        }

        public String getRepairUserId() {
            return RepairUserId;
        }

        public void setRepairUserId(String RepairUserId) {
            this.RepairUserId = RepairUserId;
        }

        public String getRepairUserName() {
            return RepairUserName;
        }

        public void setRepairUserName(String RepairUserName) {
            this.RepairUserName = RepairUserName;
        }

        public String getReportUser() {
            return ReportUser;
        }

        public void setReportUser(String ReportUser) {
            this.ReportUser = ReportUser;
        }

        public List<String> getEvalPhotoArr() {
            return EvalPhotoArr;
        }

        public void setEvalPhotoArr(List<String> EvalPhotoArr) {
            this.EvalPhotoArr = EvalPhotoArr;
        }

        public List<String> getFinishPhotoArr() {
            return FinishPhotoArr;
        }

        public void setFinishPhotoArr(List<String> FinishPhotoArr) {
            this.FinishPhotoArr = FinishPhotoArr;
        }

        public List<MaterialArrBean> getMaterialArr() {
            return MaterialArr;
        }

        public void setMaterialArr(List<MaterialArrBean> MaterialArr) {
            this.MaterialArr = MaterialArr;
        }

        public List<String> getPrspPhotoArr() {
            return PrspPhotoArr;
        }

        public void setPrspPhotoArr(List<String> PrspPhotoArr) {
            this.PrspPhotoArr = PrspPhotoArr;
        }

        public static class MaterialArrBean {
            /**
             * Amount : 2
             * Name : 灯泡
             * Price : 10
             * Spec : 15w
             */

            private String Amount;
            private String Name;
            private String Price;
            private String Spec;

            public String getAmount() {
                return Amount;
            }

            public void setAmount(String Amount) {
                this.Amount = Amount;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }

            public String getSpec() {
                return Spec;
            }

            public void setSpec(String Spec) {
                this.Spec = Spec;
            }
        }
    }
}
