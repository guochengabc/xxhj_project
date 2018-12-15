package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/8/30.
 */

public class RepairStatusBean extends RBResponse {

    /**
     * resobj : {"DispState":"4"}
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
         * DispState : 4
         */

        private String DispState;

        public String getDispState() {
            return DispState;
        }

        public void setDispState(String DispState) {
            this.DispState = DispState;
        }
    }
}
