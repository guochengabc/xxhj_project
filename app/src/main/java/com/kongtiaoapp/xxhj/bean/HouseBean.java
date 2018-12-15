package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/4/11.
 */
public class HouseBean extends RBResponse {

    /**
     * resobj : {"InPM25":"30","ProjectName":"望京(供暖项目)","ProjectId":"670a5988899b47cd94e792e33f82cfca","RunStatus":"95","City":"北京市","OptEnergy":"0Kwh","IsAlarm":"0","CollectMode":"A","InCO2":"900","ProjectType":"B","SysCOP":"0","TotalEnergy":"0Kwh","UnitEnergy":"0Kwh/m2","YStartTime":"09:00","UseDay":"0","TEndTime":"18:00","OutWeather":"多云","TStartTime":"08:00","YEndTime":"19:00","SaveEnergy":"0Kwh","InTemp":"10","OutTemp":"8"}
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
         * InPM25 : 30
         * ProjectName : 望京(供暖项目)
         * ProjectId : 670a5988899b47cd94e792e33f82cfca
         * RunStatus : 95
         * City : 北京市
         * OptEnergy : 0Kwh
         * IsAlarm : 0
         * CollectMode : A
         * InCO2 : 900
         * ProjectType : B
         * SysCOP : 0
         * TotalEnergy : 0Kwh
         * UnitEnergy : 0Kwh/m2
         * YStartTime : 09:00
         * UseDay : 0
         * TEndTime : 18:00
         * OutWeather : 多云
         * TStartTime : 08:00
         * YEndTime : 19:00
         * SaveEnergy : 0Kwh
         * InTemp : 10
         * OutTemp : 8
         */

        private String InPM25;
        private String ProjectName;
        private String ProjectId;
        private String RunStatus;
        private String City;
        private String OptEnergy;
        private String IsAlarm;
        private String CollectMode;
        private String InCO2;
        private String ProjectType;
        private String SysCOP;
        private String TotalEnergy;
        private String UnitEnergy;
        private String YStartTime;
        private String UseDay;
        private String TEndTime;
        private String OutWeather;
        private String TStartTime;
        private String YEndTime;
        private String SaveEnergy;
        private String InTemp;
        private String OutTemp;
        private String CurrentDuty;
        private String YdayEnergy;//总日总能耗
        private String OutHumidity;//室外湿度
        private String CYdayEnergy;//对比昨日能耗
        public String getCurrentDuty() {
            return CurrentDuty;
        }

        public void setCurrentDuty(String currentDuty) {
            CurrentDuty = currentDuty;
        }

        public String getInPM25() {
            return InPM25;
        }

        public void setInPM25(String InPM25) {
            this.InPM25 = InPM25;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public String getRunStatus() {
            return RunStatus;
        }

        public void setRunStatus(String RunStatus) {
            this.RunStatus = RunStatus;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getOptEnergy() {
            return OptEnergy;
        }

        public void setOptEnergy(String OptEnergy) {
            this.OptEnergy = OptEnergy;
        }

        public String getIsAlarm() {
            return IsAlarm;
        }

        public void setIsAlarm(String IsAlarm) {
            this.IsAlarm = IsAlarm;
        }

        public String getCollectMode() {
            return CollectMode;
        }

        public void setCollectMode(String CollectMode) {
            this.CollectMode = CollectMode;
        }

        public String getInCO2() {
            return InCO2;
        }

        public void setInCO2(String InCO2) {
            this.InCO2 = InCO2;
        }

        public String getProjectType() {
            return ProjectType;
        }

        public void setProjectType(String ProjectType) {
            this.ProjectType = ProjectType;
        }

        public String getSysCOP() {
            return SysCOP;
        }

        public void setSysCOP(String SysCOP) {
            this.SysCOP = SysCOP;
        }

        public String getTotalEnergy() {
            return TotalEnergy;
        }

        public void setTotalEnergy(String TotalEnergy) {
            this.TotalEnergy = TotalEnergy;
        }

        public String getUnitEnergy() {
            return UnitEnergy;
        }

        public void setUnitEnergy(String UnitEnergy) {
            this.UnitEnergy = UnitEnergy;
        }

        public String getYStartTime() {
            return YStartTime;
        }

        public void setYStartTime(String YStartTime) {
            this.YStartTime = YStartTime;
        }

        public String getUseDay() {
            return UseDay;
        }

        public void setUseDay(String UseDay) {
            this.UseDay = UseDay;
        }

        public String getTEndTime() {
            return TEndTime;
        }

        public void setTEndTime(String TEndTime) {
            this.TEndTime = TEndTime;
        }

        public String getOutWeather() {
            return OutWeather;
        }

        public void setOutWeather(String OutWeather) {
            this.OutWeather = OutWeather;
        }

        public String getTStartTime() {
            return TStartTime;
        }

        public void setTStartTime(String TStartTime) {
            this.TStartTime = TStartTime;
        }

        public String getYEndTime() {
            return YEndTime;
        }

        public void setYEndTime(String YEndTime) {
            this.YEndTime = YEndTime;
        }

        public String getSaveEnergy() {
            return SaveEnergy;
        }

        public void setSaveEnergy(String SaveEnergy) {
            this.SaveEnergy = SaveEnergy;
        }

        public String getInTemp() {
            return InTemp;
        }

        public void setInTemp(String InTemp) {
            this.InTemp = InTemp;
        }

        public String getOutTemp() {
            return OutTemp;
        }

        public void setOutTemp(String OutTemp) {
            this.OutTemp = OutTemp;
        }

        public String getYdayEnergy() {
            return YdayEnergy;
        }

        public void setYdayEnergy(String ydayEnergy) {
            YdayEnergy = ydayEnergy;
        }

        public String getOutHumidity() {
            return OutHumidity;
        }

        public void setOutHumidity(String outHumidity) {
            OutHumidity = outHumidity;
        }

        public String getCYdayEnergy() {
            return CYdayEnergy;
        }

        public void setCYdayEnergy(String CYdayEnergy) {
            this.CYdayEnergy = CYdayEnergy;
        }
    }
}
