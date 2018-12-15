package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by G_XXHJ on 2018/9/29.  变配电首页信息
 */

public class BPD_MainInfoBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"GroupData":[{"EnerParam":[{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"201有功","Param":"0.0","Type":"","Y_param":"0.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"202有功","Param":"1.0","Type":"","Y_param":"1.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"总有功","Param":"1.0","Type":"","Y_param":"1.0"}]},{"EnerParam":[{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"201无功","Param":"0.0","Type":"","Y_param":"0.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"202无功","Param":"1.0","Type":"","Y_param":"1.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"总无功","Param":"1.0","Type":"","Y_param":"1.0"}]},{"EnerParam":[{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"201负荷率","Param":"0.0","Type":"","Y_param":"0.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"202负荷率","Param":"1.0","Type":"","Y_param":"1.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"峰谷电量","Param":"1.0","Type":"","Y_param":"1.0"}]}],"ChartCateg":{"CategoryName":"分项统计","Chart":[{"ChartNum":1,"Code":"EEC","Text":"电","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}]},"ProjectId":"d23d3a7344464ee4a7545c3af4bb736c"}
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
         * GroupData : [{"EnerParam":[{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"201有功","Param":"0.0","Type":"","Y_param":"0.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"202有功","Param":"1.0","Type":"","Y_param":"1.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"总有功","Param":"1.0","Type":"","Y_param":"1.0"}]},{"EnerParam":[{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"201无功","Param":"0.0","Type":"","Y_param":"0.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"202无功","Param":"1.0","Type":"","Y_param":"1.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"总无功","Param":"1.0","Type":"","Y_param":"1.0"}]},{"EnerParam":[{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"201负荷率","Param":"0.0","Type":"","Y_param":"0.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"202负荷率","Param":"1.0","Type":"","Y_param":"1.0"},{"ChartSign":"1","DateSign":"C","DisplayType":"O","Name":"峰谷电量","Param":"1.0","Type":"","Y_param":"1.0"}]}]
         * ChartCateg : {"CategoryName":"分项统计","Chart":[{"ChartNum":1,"Code":"EEC","Text":"电","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}]}
         * ProjectId : d23d3a7344464ee4a7545c3af4bb736c
         */

        private ChartCategBean ChartCateg;
        private String ProjectId;
        private String Time;
        private List<GroupDataBean> GroupData;

        public ChartCategBean getChartCateg() {
            return ChartCateg;
        }

        public void setChartCateg(ChartCategBean ChartCateg) {
            this.ChartCateg = ChartCateg;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public List<GroupDataBean> getGroupData() {
            return GroupData;
        }

        public void setGroupData(List<GroupDataBean> GroupData) {
            this.GroupData = GroupData;
        }

        public static class ChartCategBean {
            /**
             * CategoryName : 分项统计
             * Chart : [{"ChartNum":1,"Code":"EEC","Text":"电","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}]
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

            public static class ChartBean implements Serializable{
                /**
                 * ChartNum : 1
                 * Code : EEC
                 * Text : 电
                 * TimeName : [{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]
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
                     * TimeCode : A
                     * Value : 时
                     */

                    private String TimeCode;
                    private String Value;

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
                 * ChartSign : 1
                 * DateSign : C
                 * DisplayType : O
                 * Name : 201有功
                 * Param : 0.0
                 * Type :
                 * Y_param : 0.0
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
