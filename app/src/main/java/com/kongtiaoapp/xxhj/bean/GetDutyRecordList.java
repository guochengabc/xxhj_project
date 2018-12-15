package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-12.
 * 说明:值班记录列表
 */
public class GetDutyRecordList extends RBResponse {

    /**
     * BeginTime : 1472201766
     * DutyId : c88731c582c44acbae72db2281f2b3f6
     * UserName : 大白
     * EndTime : 1472288166
     */

    private List<RecordList> resobj;

    public List<RecordList> getResobj() {
        return resobj;
    }

    public void setResobj(List<RecordList> resobj) {
        this.resobj = resobj;
    }

    public static class RecordList {
        private String BeginTime;
        private String DutyId;
        private String UserName;
        private String EndTime;
        private String DutyStatus;
        public String getBeginTime() {
            return BeginTime;
        }

        public void setBeginTime(String BeginTime) {
            this.BeginTime = BeginTime;
        }

        public String getDutyId() {
            return DutyId;
        }

        public void setDutyId(String DutyId) {
            this.DutyId = DutyId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getDutyStatus() {
            return DutyStatus;
        }

        public void setDutyStatus(String dutyStatus) {
            DutyStatus = dutyStatus;
        }
    }
}
