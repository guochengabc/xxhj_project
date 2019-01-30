package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class EnvironmentInnerBan extends RBResponse {

    /**
     * code : 40000
     * resobj : {"ChartCateg":{"CategoryName":"能耗统计","Chart":[{"ChartNum":1,"Code":"WD","Text":"温度","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"SD","Text":"湿度","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"CO2","Text":"二氧化碳","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]}]},"GroupData":[{"EnerParam":[{"DisplayType":"O","Name":"小区","Type":""},{"DisplayType":"O","Name":"温度","Type":""},{"DisplayType":"O","Name":"湿度","Type":""},{"DisplayType":"O","Name":"二氧化碳","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"东区","Type":""},{"DisplayType":"O","Name":"12℃","Type":""},{"DisplayType":"O","Name":"60","Type":""},{"DisplayType":"O","Name":"1800","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"中区","Type":""},{"DisplayType":"O","Name":"13℃","Type":""},{"DisplayType":"O","Name":"72","Type":""},{"DisplayType":"O","Name":"1970","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"西区","Type":""},{"DisplayType":"O","Name":"13.6℃","Type":""},{"DisplayType":"O","Name":"75","Type":""},{"DisplayType":"O","Name":"2106","Type":""}]}]}
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
         * ChartCateg : {"CategoryName":"能耗统计","Chart":[{"ChartNum":1,"Code":"WD","Text":"温度","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"SD","Text":"湿度","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"CO2","Text":"二氧化碳","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]}]}
         * GroupData : [{"EnerParam":[{"DisplayType":"O","Name":"小区","Type":""},{"DisplayType":"O","Name":"温度","Type":""},{"DisplayType":"O","Name":"湿度","Type":""},{"DisplayType":"O","Name":"二氧化碳","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"东区","Type":""},{"DisplayType":"O","Name":"12℃","Type":""},{"DisplayType":"O","Name":"60","Type":""},{"DisplayType":"O","Name":"1800","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"中区","Type":""},{"DisplayType":"O","Name":"13℃","Type":""},{"DisplayType":"O","Name":"72","Type":""},{"DisplayType":"O","Name":"1970","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"西区","Type":""},{"DisplayType":"O","Name":"13.6℃","Type":""},{"DisplayType":"O","Name":"75","Type":""},{"DisplayType":"O","Name":"2106","Type":""}]}]
         */

        private ChartCategBean ChartCateg;
        private List<GroupDataBean> GroupData;

        public ChartCategBean getChartCateg() {
            return ChartCateg;
        }

        public void setChartCateg(ChartCategBean ChartCateg) {
            this.ChartCateg = ChartCateg;
        }

        public List<GroupDataBean> getGroupData() {
            return GroupData;
        }

        public void setGroupData(List<GroupDataBean> GroupData) {
            this.GroupData = GroupData;
        }

        public static class ChartCategBean {
            /**
             * CategoryName : 能耗统计
             * Chart : [{"ChartNum":1,"Code":"WD","Text":"温度","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"SD","Text":"湿度","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"CO2","Text":"二氧化碳","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]}]
             */

            private String CategoryName;
            private List<ChartBean> Chart;

            public String getCategoryName() {
                return CategoryName;
            }

            public void setCategoryName(String CategoryName) {
                this.CategoryName = CategoryName;
            }

            public List<ChartBean> getChart() {
                return Chart;
            }

            public void setChart(List<ChartBean> Chart) {
                this.Chart = Chart;
            }

            public static class ChartBean {
                /**
                 * ChartNum : 1
                 * Code : WD
                 * Text : 温度
                 * TimeName : [{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]
                 */

                private int ChartNum;
                private String Code;
                private String Text;
                private List<TimeNameBean> TimeName;

                public int getChartNum() {
                    return ChartNum;
                }

                public void setChartNum(int ChartNum) {
                    this.ChartNum = ChartNum;
                }

                public String getCode() {
                    return Code;
                }

                public void setCode(String Code) {
                    this.Code = Code;
                }

                public String getText() {
                    return Text;
                }

                public void setText(String Text) {
                    this.Text = Text;
                }

                public List<TimeNameBean> getTimeName() {
                    return TimeName;
                }

                public void setTimeName(List<TimeNameBean> TimeName) {
                    this.TimeName = TimeName;
                }

                public static class TimeNameBean {
                    /**
                     * ChartSign : 0
                     * TimeCode : A
                     * Value : 时
                     */

                    private String ChartSign;
                    private String TimeCode;
                    private String Value;

                    public String getChartSign() {
                        return ChartSign;
                    }

                    public void setChartSign(String ChartSign) {
                        this.ChartSign = ChartSign;
                    }

                    public String getTimeCode() {
                        return TimeCode;
                    }

                    public void setTimeCode(String TimeCode) {
                        this.TimeCode = TimeCode;
                    }

                    public String getValue() {
                        return Value;
                    }

                    public void setValue(String Value) {
                        this.Value = Value;
                    }
                }
            }
        }

        public static class GroupDataBean {
            private List<EnerParamBean> EnerParam;

            public List<EnerParamBean> getEnerParam() {
                return EnerParam;
            }

            public void setEnerParam(List<EnerParamBean> EnerParam) {
                this.EnerParam = EnerParam;
            }

            public static class EnerParamBean {
                /**
                 * DisplayType : O
                 * Name : 小区
                 * Type :
                 */

                private String DisplayType;
                private String Name;
                private String Type;

                public String getDisplayType() {
                    return DisplayType;
                }

                public void setDisplayType(String DisplayType) {
                    this.DisplayType = DisplayType;
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
}
