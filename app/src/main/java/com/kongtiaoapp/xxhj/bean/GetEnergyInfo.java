package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-30.
 * 说明:
 */
public class GetEnergyInfo extends RBResponse {

    /**
     * ChPumpEnergyConsumption : {"Value":"value","Save":"value"}
     * TowerEnergyConsumption : {"Value":"value","Save":"value"}
     * UnitEnergyConsumption : {"Value":"value","Save":"value"}
     * CoPumpEnergyConsumption : {"Value":"value","Save":"value"}
     * SysEnergyConsumption : [{"Value":"0","Time":"00:00:10","Save":"0"},{"Value":"0","Time":"00:12:10","Save":"0"},{"Value":"0","Time":"00:12:10","Save":"0"},{"Value":"0","Time":"00:12:10","Save":"0"},{"Value":"0","Time":"00:12:10","Save":"0"}]
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
         * Value : value
         * Save : value
         */

        private ChPumpEnergyConsumptionBean ChPumpEnergyConsumption;
        /**
         * Value : value
         * Save : value
         */

        private TowerEnergyConsumptionBean TowerEnergyConsumption;
        /**
         * Value : value
         * Save : value
         */

        private UnitEnergyConsumptionBean UnitEnergyConsumption;
        /**
         * Value : value
         * Save : value
         */

        private CoPumpEnergyConsumptionBean CoPumpEnergyConsumption;
        /**
         * Value : 0
         * Time : 00:00:10
         * Save : 0
         */

        private List<SysEnergyConsumptionBean> SysEnergyConsumption;

        public ChPumpEnergyConsumptionBean getChPumpEnergyConsumption() {
            return ChPumpEnergyConsumption;
        }

        public void setChPumpEnergyConsumption(ChPumpEnergyConsumptionBean ChPumpEnergyConsumption) {
            this.ChPumpEnergyConsumption = ChPumpEnergyConsumption;
        }

        public TowerEnergyConsumptionBean getTowerEnergyConsumption() {
            return TowerEnergyConsumption;
        }

        public void setTowerEnergyConsumption(TowerEnergyConsumptionBean TowerEnergyConsumption) {
            this.TowerEnergyConsumption = TowerEnergyConsumption;
        }

        public UnitEnergyConsumptionBean getUnitEnergyConsumption() {
            return UnitEnergyConsumption;
        }

        public void setUnitEnergyConsumption(UnitEnergyConsumptionBean UnitEnergyConsumption) {
            this.UnitEnergyConsumption = UnitEnergyConsumption;
        }

        public CoPumpEnergyConsumptionBean getCoPumpEnergyConsumption() {
            return CoPumpEnergyConsumption;
        }

        public void setCoPumpEnergyConsumption(CoPumpEnergyConsumptionBean CoPumpEnergyConsumption) {
            this.CoPumpEnergyConsumption = CoPumpEnergyConsumption;
        }

        public List<SysEnergyConsumptionBean> getSysEnergyConsumption() {
            return SysEnergyConsumption;
        }

        public void setSysEnergyConsumption(List<SysEnergyConsumptionBean> SysEnergyConsumption) {
            this.SysEnergyConsumption = SysEnergyConsumption;
        }

        public static class ChPumpEnergyConsumptionBean {
            private String Value;
            private String Save;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getSave() {
                return Save;
            }

            public void setSave(String Save) {
                this.Save = Save;
            }
        }

        public static class TowerEnergyConsumptionBean {
            private String Value;
            private String Save;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getSave() {
                return Save;
            }

            public void setSave(String Save) {
                this.Save = Save;
            }
        }

        public static class UnitEnergyConsumptionBean {
            private String Value;
            private String Save;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getSave() {
                return Save;
            }

            public void setSave(String Save) {
                this.Save = Save;
            }
        }

        public static class CoPumpEnergyConsumptionBean {
            private String Value;
            private String Save;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getSave() {
                return Save;
            }

            public void setSave(String Save) {
                this.Save = Save;
            }
        }

        public static class SysEnergyConsumptionBean {
            private String Value;
            private String Time;
            private String Save;

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

            public String getSave() {
                return Save;
            }

            public void setSave(String Save) {
                this.Save = Save;
            }
        }
    }
}
