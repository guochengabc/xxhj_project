package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/6/6.
 */

public class HVACPieChartBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"DataSources":{"DataName":"图表数据源","PieData":[{"Name":"总能耗","Value":"29407","percent":"100%"},{"Name":"冷却塔能耗","Value":"470","percent":"2%"},{"Name":"冷却泵能耗","Value":"3365","percent":"11%"},{"Name":"冷机","Value":"21031","percent":"72%"},{"Name":"其他","Value":"4542","percent":"15%"}]},"RunData":[{"DateSign":"C","Name":"冷机","Type":"EC"},{"DateSign":"C","Name":"冷却塔","Type":"LQT"},{"DateSign":"C","Name":"冷冻泵","Type":"LD"},{"DateSign":"C","Name":"冷却泵","Type":"LQ"}]}
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
         * DataSources : {"DataName":"图表数据源","PieData":[{"Name":"总能耗","Value":"29407","percent":"100%"},{"Name":"冷却塔能耗","Value":"470","percent":"2%"},{"Name":"冷却泵能耗","Value":"3365","percent":"11%"},{"Name":"冷机","Value":"21031","percent":"72%"},{"Name":"其他","Value":"4542","percent":"15%"}]}
         * RunData : [{"DateSign":"C","Name":"冷机","Type":"EC"},{"DateSign":"C","Name":"冷却塔","Type":"LQT"},{"DateSign":"C","Name":"冷冻泵","Type":"LD"},{"DateSign":"C","Name":"冷却泵","Type":"LQ"}]
         */

        private DataSourcesBean DataSources;
        private List<RunDataBean> RunData;

        public DataSourcesBean getDataSources() {
            return DataSources;
        }

        public void setDataSources(DataSourcesBean DataSources) {
            this.DataSources = DataSources;
        }

        public List<RunDataBean> getRunData() {
            return RunData;
        }

        public void setRunData(List<RunDataBean> RunData) {
            this.RunData = RunData;
        }

        public static class DataSourcesBean {
            /**
             * DataName : 图表数据源
             * PieData : [{"Name":"总能耗","Value":"29407","percent":"100%"},{"Name":"冷却塔能耗","Value":"470","percent":"2%"},{"Name":"冷却泵能耗","Value":"3365","percent":"11%"},{"Name":"冷机","Value":"21031","percent":"72%"},{"Name":"其他","Value":"4542","percent":"15%"}]
             */

            private String DataName;
            private List<PieDataBean> PieData;

            public String getDataName() {
                return DataName;
            }

            public void setDataName(String DataName) {
                this.DataName = DataName;
            }

            public List<PieDataBean> getPieData() {
                return PieData;
            }

            public void setPieData(List<PieDataBean> PieData) {
                this.PieData = PieData;
            }

            public static class PieDataBean {
                /**
                 * Name : 总能耗
                 * Value : 29407
                 * percent : 100%
                 */

                private String Name;
                private String Value;
                private String percent;

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public String getValue() {
                    return Value;
                }

                public void setValue(String Value) {
                    this.Value = Value;
                }

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }
            }
        }

        public static class RunDataBean {
            /**
             * DateSign : C
             * Name : 冷机
             * Type : EC
             */

            private String DateSign;
            private String Name;
            private String Type;

            public String getDateSign() {
                return DateSign;
            }

            public void setDateSign(String DateSign) {
                this.DateSign = DateSign;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }
        }
    }
}
