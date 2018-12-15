package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2016/11/19.
 */
public class Report_Bean extends RBResponse {

    /**
     * resobj : {"RiseECRate":"100.0","HighECStd":"1000.0","ECRiseRank":"0","ReportId":"69076ea15c8848a1814230067c5813ff","COPCapacity":"-1.42","HighCOPStd":"0.28","UnitEC":"100.0","CO2":"900.0","COP":"4.08","ECRankSame":"4","DealRate":"80.0","RiseCOPRate":"100.0","TotalEC":"1338156.98","OptDevice":"1#冷机","RiseEC":"1338156.98","CreateTime":"1481010939","COPRiseRank":"0","RiseCOP":"4.08","ECRank":"26","ProjectId":"d53106e10f6b4581af0f65547d2f5f49","Temp":"25.0","COPRankSame":"26"}
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
         * RiseECRate : 100.0
         * HighECStd : 1000.0
         * ECRiseRank : 0
         * ReportId : 69076ea15c8848a1814230067c5813ff
         * COPCapacity : -1.42
         * HighCOPStd : 0.28
         * UnitEC : 100.0
         * CO2 : 900.0
         * COP : 4.08
         * ECRankSame : 4
         * DealRate : 80.0
         * RiseCOPRate : 100.0
         * TotalEC : 1338156.98
         * OptDevice : 1#冷机
         * RiseEC : 1338156.98
         * CreateTime : 1481010939
         * COPRiseRank : 0
         * RiseCOP : 4.08
         * ECRank : 26
         * ProjectId : d53106e10f6b4581af0f65547d2f5f49
         * Temp : 25.0
         * COPRankSame : 26
         */

        private String RiseECRate;
        private String HighECStd;
        private String ECRiseRank;
        private String ReportId;
        private String COPCapacity;
        private String HighCOPStd;
        private String UnitEC;
        private String CO2;
        private String COP;
        private String ECRankSame;
        private String DealRate;
        private String RiseCOPRate;
        private String TotalEC;
        private String OptDevice;
        private String RiseEC;
        private String CreateTime;
        private String COPRiseRank;
        private String RiseCOP;
        private String ECRank;
        private String ProjectId;
        private String Temp;
        private String COPRankSame;
        private String ProjectName;

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String projectName) {
            ProjectName = projectName;
        }

        public String getRiseECRate() {
            return RiseECRate;
        }

        public void setRiseECRate(String RiseECRate) {
            this.RiseECRate = RiseECRate;
        }

        public String getHighECStd() {
            return HighECStd;
        }

        public void setHighECStd(String HighECStd) {
            this.HighECStd = HighECStd;
        }

        public String getECRiseRank() {
            return ECRiseRank;
        }

        public void setECRiseRank(String ECRiseRank) {
            this.ECRiseRank = ECRiseRank;
        }

        public String getReportId() {
            return ReportId;
        }

        public void setReportId(String ReportId) {
            this.ReportId = ReportId;
        }

        public String getCOPCapacity() {
            return COPCapacity;
        }

        public void setCOPCapacity(String COPCapacity) {
            this.COPCapacity = COPCapacity;
        }

        public String getHighCOPStd() {
            return HighCOPStd;
        }

        public void setHighCOPStd(String HighCOPStd) {
            this.HighCOPStd = HighCOPStd;
        }

        public String getUnitEC() {
            return UnitEC;
        }

        public void setUnitEC(String UnitEC) {
            this.UnitEC = UnitEC;
        }

        public String getCO2() {
            return CO2;
        }

        public void setCO2(String CO2) {
            this.CO2 = CO2;
        }

        public String getCOP() {
            return COP;
        }

        public void setCOP(String COP) {
            this.COP = COP;
        }

        public String getECRankSame() {
            return ECRankSame;
        }

        public void setECRankSame(String ECRankSame) {
            this.ECRankSame = ECRankSame;
        }

        public String getDealRate() {
            return DealRate;
        }

        public void setDealRate(String DealRate) {
            this.DealRate = DealRate;
        }

        public String getRiseCOPRate() {
            return RiseCOPRate;
        }

        public void setRiseCOPRate(String RiseCOPRate) {
            this.RiseCOPRate = RiseCOPRate;
        }

        public String getTotalEC() {
            return TotalEC;
        }

        public void setTotalEC(String TotalEC) {
            this.TotalEC = TotalEC;
        }

        public String getOptDevice() {
            return OptDevice;
        }

        public void setOptDevice(String OptDevice) {
            this.OptDevice = OptDevice;
        }

        public String getRiseEC() {
            return RiseEC;
        }

        public void setRiseEC(String RiseEC) {
            this.RiseEC = RiseEC;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCOPRiseRank() {
            return COPRiseRank;
        }

        public void setCOPRiseRank(String COPRiseRank) {
            this.COPRiseRank = COPRiseRank;
        }

        public String getRiseCOP() {
            return RiseCOP;
        }

        public void setRiseCOP(String RiseCOP) {
            this.RiseCOP = RiseCOP;
        }

        public String getECRank() {
            return ECRank;
        }

        public void setECRank(String ECRank) {
            this.ECRank = ECRank;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public String getTemp() {
            return Temp;
        }

        public void setTemp(String Temp) {
            this.Temp = Temp;
        }

        public String getCOPRankSame() {
            return COPRankSame;
        }

        public void setCOPRankSame(String COPRankSame) {
            this.COPRankSame = COPRankSame;
        }
    }
}
