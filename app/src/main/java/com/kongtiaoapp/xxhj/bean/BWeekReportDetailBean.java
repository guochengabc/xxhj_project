package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/10/11.  变配电抄表详情
 */

public class BWeekReportDetailBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Data":[{"Time":"2018-09-02","WeekReport":[{"Remark":"1.1.本周电量为0.0}kWh,同比上周下降0%,201有功为0.0kWh,202有功为0.0kWh","Category":"1"},{"Remark":"2.本周最大负荷为0%,同比上周0%,201最大负荷为0%,202最大负荷为0%","Category":"2"},{"Remark":"3.本周尖峰平谷电量分别为0.0kWh,0.0kWh,0.0kWh,0.0kWh,总体比上周0.0%","Category":"3"}]},{"Time":"2018-09-09","WeekReport":[{"Remark":"1.1.本周电量为0.0}kWh,同比上周下降0%,201有功为0.0kWh,202有功为0.0kWh","Category":"1"},{"Remark":"2.本周最大负荷为0%,同比上周0%,201最大负荷为0%,202最大负荷为0%","Category":"2"},{"Remark":"3.本周尖峰平谷电量分别为0.0kWh,0.0kWh,0.0kWh,0.0kWh,总体比上周0.0%","Category":"3"}]}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<DataBean> Data;

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean {
            /**
             * Time : 2018-09-02
             * WeekReport : [{"Remark":"1.1.本周电量为0.0}kWh,同比上周下降0%,201有功为0.0kWh,202有功为0.0kWh","Category":"1"},{"Remark":"2.本周最大负荷为0%,同比上周0%,201最大负荷为0%,202最大负荷为0%","Category":"2"},{"Remark":"3.本周尖峰平谷电量分别为0.0kWh,0.0kWh,0.0kWh,0.0kWh,总体比上周0.0%","Category":"3"}]
             */

            private String Time;
            private List<WeekReportBean> WeekReport;

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            public List<WeekReportBean> getWeekReport() {
                return WeekReport;
            }

            public void setWeekReport(List<WeekReportBean> WeekReport) {
                this.WeekReport = WeekReport;
            }

            public static class WeekReportBean {
                /**
                 * Remark : 1.1.本周电量为0.0}kWh,同比上周下降0%,201有功为0.0kWh,202有功为0.0kWh
                 * Category : 1
                 */

                private String Remark;
                private String Category;

                public String getRemark() {
                    return Remark;
                }

                public void setRemark(String Remark) {
                    this.Remark = Remark;
                }

                public String getCategory() {
                    return Category;
                }

                public void setCategory(String Category) {
                    this.Category = Category;
                }
            }
        }
    }
}
