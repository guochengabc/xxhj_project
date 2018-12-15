package com.kongtiaoapp.xxhj.bean;

/**
 * Created by G_XXHJ on 2018/7/18.
 */

public class EnergyRecordGetValueBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"LastMonth":50.1,"Percentage":"+100%","ThisMonth":100.1}
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
         * LastMonth : 50.1
         * Percentage : +100%
         * ThisMonth : 100.1
         */

        private double LastMonth;
        private String Percentage;
        private double ThisMonth;

        public double getLastMonth() {
            return LastMonth;
        }

        public void setLastMonth(double LastMonth) {
            this.LastMonth = LastMonth;
        }

        public String getPercentage() {
            return Percentage;
        }

        public void setPercentage(String Percentage) {
            this.Percentage = Percentage;
        }

        public double getThisMonth() {
            return ThisMonth;
        }

        public void setThisMonth(double ThisMonth) {
            this.ThisMonth = ThisMonth;
        }
    }
}
