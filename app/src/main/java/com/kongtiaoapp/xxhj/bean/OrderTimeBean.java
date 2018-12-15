package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/8/11.
 */

public class OrderTimeBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"OrderTime":"2017-08-11 15:21:53"}
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
         * OrderTime : 2017-08-11 15:21:53
         */

        private String OrderTime;

        public String getOrderTime() {
            return OrderTime;
        }

        public void setOrderTime(String OrderTime) {
            this.OrderTime = OrderTime;
        }
    }
}
