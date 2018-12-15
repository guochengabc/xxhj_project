package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-14.
 * 说明:
 */
public class ZhenDuan extends RBResponse {

    /**
     * SysCoolingCapacity : [{"Value":"0.04","Time":"14:47:43"},{"Value":"0.04","Time":"14:47:53"},{"Value":"0.04","Time":"14:48:03"},{"Value":"0.04","Time":"14:48:13"}]
     * ProjectName : 北京万达广场
     * Building : 10000
     * UnitRunningCop : [{"Value":"0.04","Time":"14:47:43"},{"Value":"0.04","Time":"14:47:53"},{"Value":"0.04","Time":"14:48:03"},{"Value":"0.04","Time":"14:48:13"}]
     * SysRunningCop : [{"Value":"0.04","Time":"14:47:53"},{"Value":"0.04","Time":"14:48:03"},{"Value":"0.04","Time":"14:48:13"}]
     * SysEnergyConsumption : [{"Value":"0.04","Time":"14:47:53"},{"Value":"0.04","Time":"14:48:03"},{"Value":"0.04","Time":"14:48:13"}]
     * SysCapacity : {"Result":"1.48","Description":"空调系统机组容量配置太大，机组容量不能有效利用。","Item":"冷水机组容量系统配置（单位制冷量）","ValueInterval":"0.0,0.0,0.0,0.0"}
     * DiagTime : 1471938600
     * SysCOP : {"Result":"3.96","Description":"该机组低于2015公共建筑节能设计标准限定值，建议更换","Item":"系统性能系数（SysCOP）","ValueInterval":"4.50, 4.60, 4.70, 4.80"}
     * CoolingArea : 10000
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private String ProjectName;
        private String Building;
        /**
         * Result : 1.48
         * Description : 空调系统机组容量配置太大，机组容量不能有效利用。
         * Item : 冷水机组容量系统配置（单位制冷量）
         * ValueInterval : 0.0,0.0,0.0,0.0
         */

        private SysCapacityBean SysCapacity;
        private String DiagTime;
        /**
         * Result : 3.96
         * Description : 该机组低于2015公共建筑节能设计标准限定值，建议更换
         * Item : 系统性能系数（SysCOP）
         * ValueInterval : 4.50, 4.60, 4.70, 4.80
         */

        private SysCOPBean SysCOP;

        private SysEcrBean SysEcr;
        private String CoolingArea;
        /**
         * Value : 0.04
         * Time : 14:47:43
         */

        private List<SysCoolingCapacityBean> SysCoolingCapacity;
        /**
         * Value : 0.04
         * Time : 14:47:43
         */

        private List<UnitRunningCopBean> UnitRunningCop;
        /**
         * Value : 0.04
         * Time : 14:47:53
         */

        private List<SysRunningCopBean> SysRunningCop;
        /**
         * Value : 0.04
         * Time : 14:47:53
         */

        private List<SysEnergyConsumptionBean> SysEnergyConsumption;

        public SysEcrBean getSysEcr() {
            return SysEcr;
        }

        public void setSysEcr(SysEcrBean sysEcr) {
            SysEcr = sysEcr;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getBuilding() {
            return Building;
        }

        public void setBuilding(String Building) {
            this.Building = Building;
        }

        public SysCapacityBean getSysCapacity() {
            return SysCapacity;
        }

        public void setSysCapacity(SysCapacityBean SysCapacity) {
            this.SysCapacity = SysCapacity;
        }

        public String getDiagTime() {
            return DiagTime;
        }

        public void setDiagTime(String DiagTime) {
            this.DiagTime = DiagTime;
        }

        public SysCOPBean getSysCOP() {
            return SysCOP;
        }

        public void setSysCOP(SysCOPBean SysCOP) {
            this.SysCOP = SysCOP;
        }

        public String getCoolingArea() {
            return CoolingArea;
        }

        public void setCoolingArea(String CoolingArea) {
            this.CoolingArea = CoolingArea;
        }

        public List<SysCoolingCapacityBean> getSysCoolingCapacity() {
            return SysCoolingCapacity;
        }

        public void setSysCoolingCapacity(List<SysCoolingCapacityBean> SysCoolingCapacity) {
            this.SysCoolingCapacity = SysCoolingCapacity;
        }

        public List<UnitRunningCopBean> getUnitRunningCop() {
            return UnitRunningCop;
        }

        public void setUnitRunningCop(List<UnitRunningCopBean> UnitRunningCop) {
            this.UnitRunningCop = UnitRunningCop;
        }

        public List<SysRunningCopBean> getSysRunningCop() {
            return SysRunningCop;
        }

        public void setSysRunningCop(List<SysRunningCopBean> SysRunningCop) {
            this.SysRunningCop = SysRunningCop;
        }

        public List<SysEnergyConsumptionBean> getSysEnergyConsumption() {
            return SysEnergyConsumption;
        }

        public void setSysEnergyConsumption(List<SysEnergyConsumptionBean> SysEnergyConsumption) {
            this.SysEnergyConsumption = SysEnergyConsumption;
        }

        public static class SysCapacityBean {
            private String Result;
            private String Description;
            private String Item;
            private String ValueInterval;

            public String getResult() {
                return Result;
            }

            public void setResult(String Result) {
                this.Result = Result;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getItem() {
                return Item;
            }

            public void setItem(String Item) {
                this.Item = Item;
            }

            public String getValueInterval() {
                return ValueInterval;
            }

            public void setValueInterval(String ValueInterval) {
                this.ValueInterval = ValueInterval;
            }
        }

        public static class SysCOPBean {
            private String Result;
            private String Description;
            private String Item;
            private String ValueInterval;

            public String getResult() {
                return Result;
            }

            public void setResult(String Result) {
                this.Result = Result;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getItem() {
                return Item;
            }

            public void setItem(String Item) {
                this.Item = Item;
            }

            public String getValueInterval() {
                return ValueInterval;
            }

            public void setValueInterval(String ValueInterval) {
                this.ValueInterval = ValueInterval;
            }
        }

        public static class SysCoolingCapacityBean {
            private String Value;
            private String Time;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }
        }

        public static class UnitRunningCopBean {
            private String Value;
            private String Time;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }
        }

        public static class SysRunningCopBean {
            private String Value;
            private String Time;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }
        }

        public static class SysEnergyConsumptionBean {
            private String Value;
            private String Time;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }
        }
        public static class SysEcrBean{
            private String Result;
            private String Description;//说明
            private String Item;//名字
            private String ValueInterval;

            public String getResult() {
                return Result;
            }

            public void setResult(String result) {
                Result = result;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String description) {
                Description = description;
            }

            public String getItem() {
                return Item;
            }

            public void setItem(String item) {
                Item = item;
            }

            public String getValueInterval() {
                return ValueInterval;
            }

            public void setValueInterval(String valueInterval) {
                ValueInterval = valueInterval;
            }
        }
    }
}
