package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/3/17.
 */
public class DutyInfoBean extends RBResponse {

    /**
     * resobj : {"UserName":"1488988800","Phone":"888888"}
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
         * UserName : 1488988800
         * Phone : 888888
         */

        private String UserName;
        private String Phone;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }
    }
}
