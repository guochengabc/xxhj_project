package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/7/11.  能源计量头部
 */

public class EnergyMeterBean extends RBResponse {


    /**
     * code : 40000
     * resobj : {"ChartCateg":{"CategoryName":"能耗计量","Chart":[{"ChartNum":1,"Code":"EEC","Text":"系统统计","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"},{"ChartSign":"1","TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"WT","Text":"分组统计","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"},{"ChartSign":"1","TimeCode":"C","Value":"月"}]}]},"CousCategory":[{"CategSign":"Power","Value":"电"},{"CategSign":"Water","Value":"水"},{"CategSign":"Gas","Value":"气"}],"EnerAnaly":{"AnalyName":"能耗分析","AnalyParam":[{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"单位能耗"},{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"空调能耗"},{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"照明能耗"}]},"GroupData":[{"EnerParam":[{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"电","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"水","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"气","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试1号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试2号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试3号","Param":"3000.0","Type":"","Y_param":"-0.0"}]},{"EnerParam":[{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"电","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"水","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"气","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试1号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试2号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试3号","Param":"3000.0","Type":"","Y_param":"-0.0"}]}]}
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
         * ChartCateg : {"CategoryName":"能耗计量","Chart":[{"ChartNum":1,"Code":"EEC","Text":"系统统计","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"},{"ChartSign":"1","TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"WT","Text":"分组统计","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"},{"ChartSign":"1","TimeCode":"C","Value":"月"}]}]}
         * CousCategory : [{"CategSign":"Power","Value":"电"},{"CategSign":"Water","Value":"水"},{"CategSign":"Gas","Value":"气"}]
         * EnerAnaly : {"AnalyName":"能耗分析","AnalyParam":[{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"单位能耗"},{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"空调能耗"},{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"照明能耗"}]}
         * GroupData : [{"EnerParam":[{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"电","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"水","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"气","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试1号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试2号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试3号","Param":"3000.0","Type":"","Y_param":"-0.0"}]},{"EnerParam":[{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"电","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"水","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"气","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试1号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试2号","Param":"3000.0","Type":"","Y_param":"-0.0"},{"ChartSign":"0","DateSign":"C","DisplayType":"O","Name":"测试3号","Param":"3000.0","Type":"","Y_param":"-0.0"}]}]
         */

        private ChartCategBean ChartCateg;
        private EnerAnalyBean EnerAnaly;
        private List<CousCategoryBean> CousCategory;
        private List<GroupDataBean> GroupData;

        public ChartCategBean getChartCateg() {
            return ChartCateg;
        }

        public void setChartCateg(ChartCategBean ChartCateg) {
            this.ChartCateg = ChartCateg;
        }

        public EnerAnalyBean getEnerAnaly() {
            return EnerAnaly;
        }

        public void setEnerAnaly(EnerAnalyBean EnerAnaly) {
            this.EnerAnaly = EnerAnaly;
        }

        public List<CousCategoryBean> getCousCategory() {
            return CousCategory;
        }

        public void setCousCategory(List<CousCategoryBean> CousCategory) {
            this.CousCategory = CousCategory;
        }

        public List<GroupDataBean> getGroupData() {
            return GroupData;
        }

        public void setGroupData(List<GroupDataBean> GroupData) {
            this.GroupData = GroupData;
        }

        public static class ChartCategBean {
            /**
             * CategoryName : 能耗计量
             * Chart : [{"ChartNum":1,"Code":"EEC","Text":"系统统计","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"},{"ChartSign":"1","TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"WT","Text":"分组统计","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"},{"ChartSign":"1","TimeCode":"C","Value":"月"}]}]
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
                 * Code : EEC
                 * Text : 系统统计
                 * TimeName : [{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"},{"ChartSign":"1","TimeCode":"C","Value":"月"}]
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

        public static class EnerAnalyBean {
            /**
             * AnalyName : 能耗分析
             * AnalyParam : [{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"单位能耗"},{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"空调能耗"},{"Min":0,"Compared":["实际","国标"],"Max":6.6,"Value":[0,5.5],"Name":"照明能耗"}]
             */

            private String AnalyName;
            private List<AnalyParamBean> AnalyParam;

            public String getAnalyName() {
                return AnalyName;
            }

            public void setAnalyName(String AnalyName) {
                this.AnalyName = AnalyName;
            }

            public List<AnalyParamBean> getAnalyParam() {
                return AnalyParam;
            }

            public void setAnalyParam(List<AnalyParamBean> AnalyParam) {
                this.AnalyParam = AnalyParam;
            }

            public static class AnalyParamBean {
                /**
                 * Min : 0
                 * Compared : ["实际","国标"]
                 * Max : 6.6
                 * Value : [0,5.5]
                 * Name : 单位能耗
                 */

                private double Min;
                private double Max;
                private String Name;
                private List<String> Compared;
                private List<Double> Value;

                public double getMin() {
                    return Min;
                }

                public void setMin(double Min) {
                    this.Min = Min;
                }

                public double getMax() {
                    return Max;
                }

                public void setMax(double Max) {
                    this.Max = Max;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public List<String> getCompared() {
                    return Compared;
                }

                public void setCompared(List<String> Compared) {
                    this.Compared = Compared;
                }

                public List<Double> getValue() {
                    return Value;
                }

                public void setValue(List<Double> Value) {
                    this.Value = Value;
                }
            }
        }

        public static class CousCategoryBean {
            /**
             * CategSign : Power
             * Value : 电
             */

            private String CategSign;
            private String Value;

            public String getCategSign() {
                return CategSign;
            }

            public void setCategSign(String CategSign) {
                this.CategSign = CategSign;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
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
                 * ChartSign : 0
                 * DateSign : C
                 * DisplayType : O
                 * Name : 电
                 * Param : 3000.0
                 * Type :
                 * Y_param : -0.0
                 */

                private String ChartSign;
                private String DateSign;
                private String DisplayType;
                private String Name;
                private String Param;
                private String Type;
                private String Y_param;

                public String getChartSign() {
                    return ChartSign;
                }

                public void setChartSign(String ChartSign) {
                    this.ChartSign = ChartSign;
                }

                public String getDateSign() {
                    return DateSign;
                }

                public void setDateSign(String DateSign) {
                    this.DateSign = DateSign;
                }

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

                public String getParam() {
                    return Param;
                }

                public void setParam(String Param) {
                    this.Param = Param;
                }

                public String getType() {
                    return Type;
                }

                public void setType(String Type) {
                    this.Type = Type;
                }

                public String getY_param() {
                    return Y_param;
                }

                public void setY_param(String Y_param) {
                    this.Y_param = Y_param;
                }
            }
        }
    }
}
