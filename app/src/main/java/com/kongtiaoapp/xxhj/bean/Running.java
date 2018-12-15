package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-9-5.
 * 说明:运行首页数据
 */
public class Running extends RBResponse {

    /**
     * ProjectName : 万达广场项目
     * UseDay : 0
     * TotalEnergy : 0
     * SaveEnergy : 0
     * SysCOP : 3.94
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private String ProjectName;//项目名称
        private String UseDay;//使用天数
        private String TotalEnergy;//总功耗
        private String SaveEnergy;//节能潜力
        private String SysCOP;//cop值
        private String IsAlarm;//是否报警  0 不报警 1 报警
        private String RunStatus;//运行状态
        private String CurrentDuty;//当前值班人
        private String OptEnergy;//优化后能耗
        private String UnitEnergy;//单位能耗
        private String ProjectId;//项目id
        private String ProjectType;//项目类型
        private String CollectMode;//是否是自动采集项目

        public String getOptEnergy() {
            return OptEnergy;
        }

        public void setOptEnergy(String optEnergy) {
            OptEnergy = optEnergy;
        }

        public String getUnitEnergy() {
            return UnitEnergy;
        }

        public void setUnitEnergy(String unitEnergy) {
            UnitEnergy = unitEnergy;
        }

        public String getIsAlarm() {
            return IsAlarm;
        }

        public void setIsAlarm(String isAlarm) {
            IsAlarm = isAlarm;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getUseDay() {
            return UseDay;
        }

        public void setUseDay(String UseDay) {
            this.UseDay = UseDay;
        }

        public String getTotalEnergy() {
            return TotalEnergy;
        }

        public void setTotalEnergy(String TotalEnergy) {
            this.TotalEnergy = TotalEnergy;
        }

        public String getSaveEnergy() {
            return SaveEnergy;
        }

        public void setSaveEnergy(String SaveEnergy) {
            this.SaveEnergy = SaveEnergy;
        }

        public String getSysCOP() {
            return SysCOP;
        }

        public void setSysCOP(String SysCOP) {
            this.SysCOP = SysCOP;
        }

        public String getRunStatus() {
            return RunStatus;
        }

        public void setRunStatus(String runStatus) {
            RunStatus = runStatus;
        }

        public String getCurrentDuty() {
            return CurrentDuty;
        }

        public void setCurrentDuty(String currentDuty) {
            CurrentDuty = currentDuty;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String projectId) {
            ProjectId = projectId;
        }

        public String getProjectType() {
            return ProjectType;
        }

        public void setProjectType(String projectType) {
            ProjectType = projectType;
        }

        public String getCollectMode() {
            return CollectMode;
        }

        public void setCollectMode(String collectMode) {
            CollectMode = collectMode;
        }
    }
}
