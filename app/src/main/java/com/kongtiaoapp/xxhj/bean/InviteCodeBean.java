package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/4/8.
 */
public class InviteCodeBean extends RBResponse {

    /**
     * resobj : {"InviteCode":"0wd8ve"}
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
         * InviteCode : 0wd8ve
         */

        private String InviteCode;

        public String getInviteCode() {
            return InviteCode;
        }

        public void setInviteCode(String InviteCode) {
            this.InviteCode = InviteCode;
        }
    }
}
