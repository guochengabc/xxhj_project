package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-9-13.
 * 说明:
 */
public class GetZhiBanInfo extends RBResponse {


    /**
     * code : 40000
     * resobj : {"DutyId":"6e42a7e59d8d46e5832421fe147c0d85","LeaderAssign":"好好干活，别偷懒","OffDutyId":"420d5739d17bd0cbd343a2becbd70275","EndTime":"1472288166","SuccessorMember":"索隆","BeginTime":"1472201766","SuccessorPhone":"13701010102","Keyworks":"值班重点工作内容","WorkContents":"值班工作内容","OffDutyName":"鸣人","OffDutyPhone":"13701010105","SuccessorId":"b4fd70c18307472690661dae63d18d5c","SuccessorName":"小溪","OffDutyMember":"索隆"}
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
         * DutyId : 6e42a7e59d8d46e5832421fe147c0d85
         * LeaderAssign : 好好干活，别偷懒
         * OffDutyId : 420d5739d17bd0cbd343a2becbd70275
         * EndTime : 1472288166
         * SuccessorMember : 索隆
         * BeginTime : 1472201766
         * SuccessorPhone : 13701010102
         * Keyworks : 值班重点工作内容
         * WorkContents : 值班工作内容
         * OffDutyName : 鸣人
         * OffDutyPhone : 13701010105
         * SuccessorId : b4fd70c18307472690661dae63d18d5c
         * SuccessorName : 小溪
         * OffDutyMember : 索隆
         */

        private String DutyId;
        private String LeaderAssign;
        private String OffDutyId;
        private String EndTime;
        private String SuccessorMember;
        private String BeginTime;
        private String SuccessorPhone;
        private String Keyworks;
        private String WorkContents;
        private String OffDutyName;
        private String OffDutyPhone;
        private String SuccessorId;
        private String SuccessorName;
        private String OffDutyMember;
        private String DutyStatus;
        private String DutyStatusName;
        public String getDutyId() {
            return DutyId;
        }

        public void setDutyId(String DutyId) {
            this.DutyId = DutyId;
        }

        public String getLeaderAssign() {
            return LeaderAssign;
        }

        public void setLeaderAssign(String LeaderAssign) {
            this.LeaderAssign = LeaderAssign;
        }

        public String getOffDutyId() {
            return OffDutyId;
        }

        public void setOffDutyId(String OffDutyId) {
            this.OffDutyId = OffDutyId;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getSuccessorMember() {
            return SuccessorMember;
        }

        public void setSuccessorMember(String SuccessorMember) {
            this.SuccessorMember = SuccessorMember;
        }

        public String getBeginTime() {
            return BeginTime;
        }

        public void setBeginTime(String BeginTime) {
            this.BeginTime = BeginTime;
        }

        public String getSuccessorPhone() {
            return SuccessorPhone;
        }

        public void setSuccessorPhone(String SuccessorPhone) {
            this.SuccessorPhone = SuccessorPhone;
        }

        public String getKeyworks() {
            return Keyworks;
        }

        public void setKeyworks(String Keyworks) {
            this.Keyworks = Keyworks;
        }

        public String getWorkContents() {
            return WorkContents;
        }

        public void setWorkContents(String WorkContents) {
            this.WorkContents = WorkContents;
        }

        public String getOffDutyName() {
            return OffDutyName;
        }

        public void setOffDutyName(String OffDutyName) {
            this.OffDutyName = OffDutyName;
        }

        public String getOffDutyPhone() {
            return OffDutyPhone;
        }

        public void setOffDutyPhone(String OffDutyPhone) {
            this.OffDutyPhone = OffDutyPhone;
        }

        public String getSuccessorId() {
            return SuccessorId;
        }

        public void setSuccessorId(String SuccessorId) {
            this.SuccessorId = SuccessorId;
        }

        public String getSuccessorName() {
            return SuccessorName;
        }

        public void setSuccessorName(String SuccessorName) {
            this.SuccessorName = SuccessorName;
        }

        public String getOffDutyMember() {
            return OffDutyMember;
        }

        public void setOffDutyMember(String OffDutyMember) {
            this.OffDutyMember = OffDutyMember;
        }

        public String getDutyStatus() {
            return DutyStatus;
        }

        public void setDutyStatus(String dutyStatus) {
            DutyStatus = dutyStatus;
        }

        public String getDutyStatusName() {
            return DutyStatusName;
        }

        public void setDutyStatusName(String dutyStatusName) {
            DutyStatusName = dutyStatusName;
        }
    }
}
