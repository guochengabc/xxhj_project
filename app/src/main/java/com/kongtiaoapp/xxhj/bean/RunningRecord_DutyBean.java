package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/3/10.
 */
public class RunningRecord_DutyBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"UserName":"小溪","UserId":"b4fd70c18307472690661dae63d18d5c","Time":"1489130961","RecordId":"58c255d127e10d2b38858ade"}]
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
         * UserName : 小溪
         * UserId : b4fd70c18307472690661dae63d18d5c
         * Time : 1489130961
         * RecordId : 58c255d127e10d2b38858ade
         */

        private String UserName;
        private String UserId;
        private String Time;
        private String RecordId;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getRecordId() {
            return RecordId;
        }

        public void setRecordId(String RecordId) {
            this.RecordId = RecordId;
        }
    }
}
