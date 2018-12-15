package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/10/11.  变配电周报月报
 */

public class BWeekReportBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"MonthData":[{"Name":"9月2日","ReportId":"04e342fc847f4d098863ba0b977d622e","Date":"2018-09-02 17:30:00"},{"Name":"9月9日","ReportId":"04e342fc847f4d098863ba0b977d622e","Date":"2018-09-09 17:30:00"},{"Name":"9月16日","ReportId":"04e342fc847f4d098863ba0b977d622e","Date":"2018-09-16 17:30:00"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<MonthDataBean> MonthData;

        public List<MonthDataBean> getMonthData() {
            return MonthData;
        }

        public void setMonthData(List<MonthDataBean> MonthData) {
            this.MonthData = MonthData;
        }

        public static class MonthDataBean {
            /**
             * Name : 9月2日
             * ReportId : 04e342fc847f4d098863ba0b977d622e
             * Date : 2018-09-02 17:30:00
             */

            private String Name;
            private String ReportId;
            private String CrateTime;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getReportId() {
                return ReportId;
            }

            public void setReportId(String ReportId) {
                this.ReportId = ReportId;
            }

            public String getCrateTime() {
                return CrateTime;
            }

            public void setCrateTime(String crateTime) {
                CrateTime = crateTime;
            }
        }
    }
}
