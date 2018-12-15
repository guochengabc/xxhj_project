package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/3/28.
 */
public class GetSucceedRecordBean extends RBResponse {

    /**
     * resobj : {"DutyId":"e7fa6307e0064704a200b782b54c6248","OffDutyName":"鸣人","OffDutyTime":"1472288166000","OffDutyPhone":"13701010105","Keyworks":"值班重点工作内容","DutyStatus":"1"}
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
         * DutyId : e7fa6307e0064704a200b782b54c6248
         * OffDutyName : 鸣人
         * OffDutyTime : 1472288166000
         * OffDutyPhone : 13701010105
         * Keyworks : 值班重点工作内容
         * DutyStatus : 1
         */

        private String DutyId;
        private String OffDutyName;
        private String OffDutyTime;
        private String OffDutyPhone;
        private String Keyworks;
        private String DutyStatus;

        public String getDutyId() {
            return DutyId;
        }

        public void setDutyId(String DutyId) {
            this.DutyId = DutyId;
        }

        public String getOffDutyName() {
            return OffDutyName;
        }

        public void setOffDutyName(String OffDutyName) {
            this.OffDutyName = OffDutyName;
        }

        public String getOffDutyTime() {
            return OffDutyTime;
        }

        public void setOffDutyTime(String OffDutyTime) {
            this.OffDutyTime = OffDutyTime;
        }

        public String getOffDutyPhone() {
            return OffDutyPhone;
        }

        public void setOffDutyPhone(String OffDutyPhone) {
            this.OffDutyPhone = OffDutyPhone;
        }

        public String getKeyworks() {
            return Keyworks;
        }

        public void setKeyworks(String Keyworks) {
            this.Keyworks = Keyworks;
        }

        public String getDutyStatus() {
            return DutyStatus;
        }

        public void setDutyStatus(String DutyStatus) {
            this.DutyStatus = DutyStatus;
        }
    }
}
