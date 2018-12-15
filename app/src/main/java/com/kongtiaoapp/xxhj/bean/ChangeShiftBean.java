package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/4.  交接班类型
 */

public class ChangeShiftBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"DeviceId":"6745f94d1deffe3547fccdec65c11235","Info":{"PowerDuty":[{"CShifts":"8点交班人","Mark":"System_POffDuty8"},{"CShifts":"8点接班人","Mark":"System_PSuccessor8"},{"CShifts":"20点交班人","Mark":"System_POffDuty20"},{"CShifts":"20点接班人","Mark":"System_PSuccessor20"}]},"Name":"配电室交接班"},{"DeviceId":"6745f94jkgfd653547fccdec65c11235","Info":{"PowerDuty":[{"CShifts":"8点交班人","Mark":"System_HOffDuty8"},{"CShifts":"8点接班人","Mark":"System_HSuccessor8"},{"CShifts":"20点交班人","Mark":"Hystem_POffDuty20"},{"CShifts":"20点接班人","Mark":"System_HSuccessor20"}]},"Name":"供暖交接班"}]
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
         * DeviceId : 6745f94d1deffe3547fccdec65c11235
         * Info : {"PowerDuty":[{"CShifts":"8点交班人","Mark":"System_POffDuty8"},{"CShifts":"8点接班人","Mark":"System_PSuccessor8"},{"CShifts":"20点交班人","Mark":"System_POffDuty20"},{"CShifts":"20点接班人","Mark":"System_PSuccessor20"}]}
         * Name : 配电室交接班
         */

        private String DeviceId;
        private InfoBean Info;
        private String Name;

        public String getDeviceId() {
            return DeviceId;
        }

        public void setDeviceId(String DeviceId) {
            this.DeviceId = DeviceId;
        }

        public InfoBean getInfo() {
            return Info;
        }

        public void setInfo(InfoBean Info) {
            this.Info = Info;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public static class InfoBean {
            private List<PowerDutyBean> PowerDuty;

            public List<PowerDutyBean> getPowerDuty() {
                return PowerDuty;
            }

            public void setPowerDuty(List<PowerDutyBean> PowerDuty) {
                this.PowerDuty = PowerDuty;
            }

            public static class PowerDutyBean {
                /**
                 * CShifts : 8点交班人
                 * Mark : System_POffDuty8
                 */

                private String CShifts;
                private String Mark;

                public String getCShifts() {
                    return CShifts;
                }

                public void setCShifts(String CShifts) {
                    this.CShifts = CShifts;
                }

                public String getMark() {
                    return Mark;
                }

                public void setMark(String Mark) {
                    this.Mark = Mark;
                }
            }
        }
    }
}
