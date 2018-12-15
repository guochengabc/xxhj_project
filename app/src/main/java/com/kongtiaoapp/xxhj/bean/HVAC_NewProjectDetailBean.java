package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by G_XXHJ on 2018/5/10.
 */

public class HVAC_NewProjectDetailBean extends RBResponse {


    /**
     * code : 40000
     * resobj : {"DiagCategory":[{"CategoryName":"能耗计量","Chart":[{"ChartNum":1,"Code":"EC","Text":"电能消耗","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"QH","Text":"冷量消耗","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]}]}],"DutyInfo":{},"EfficParaInfo":[{"ChartSign":"0","DateSign":"C","Name":"冷冻供","Type":"CoLWT","Value":"11.9℃"},{"ChartSign":"0","DateSign":"C","Name":"冷冻回","Type":"CoEWT","Value":"13.4℃"},{"ChartSign":"0","DateSign":"C","Name":"冷却供","Type":"ChLWT","Value":"34.1℃"},{"ChartSign":"0","DateSign":"C","Name":"冷却回","Type":"ChEWT","Value":"31.9℃"},{"ChartSign":"0","DateSign":"C","Name":"负载率","Type":"PR","Value":"34.8%"}],"EnergyParaInfo":[{"ChartSign":"0","DateSign":"C","Name":"电耗(Kwh)","Param":"547.6","Type":"TO","Y_param":"-75%"},{"ChartSign":"0","DateSign":"C","Name":"冷量(Mwh)","Param":"0.7","Type":"QH","Y_param":"-80%"},{"ChartSign":"0","DateSign":"C","Name":"燃气(Nm³)","Param":"0","Type":"GCH","Y_param":"0"},{"ChartSign":"0","DateSign":"C","Name":"室外(℃)","Param":"21","Type":"OW","Y_param":"-3℃"},{"ChartSign":"0","DateSign":"C","Name":"湿球(℃)","Param":"17","Type":"OW","Y_param":"+1℃"},{"ChartSign":"0","DateSign":"C","Name":"室外(%)","Param":"62","Type":"OW","Y_param":"+18%"}],"ProjectId":"ac17ea95ae5d438b9000efce3fec6ee1","ProjectInfo":{"BuildingType":"行政办公建筑","City":"北京市","CoolingArea":"","EndDate":"1542211200","HeatingArea":"116000","ProjectName":"望京UCP(制冷)","ProjectType":"A","StartDate":"1524672000"},"RunStatus":"差","SysParamInfo":[{"ChartSign":"0","DateSign":"C","Name":"COP(国标)","Standard":"4.2","Type":"LCOP","Value":"1.7"},{"ChartSign":"0","DateSign":"C","Name":"SCOP(国标)","Standard":"4.4","Type":"SCOP","Value":"1.2"},{"ChartSign":"1","DateSign":"C","Name":"巡检评价","Standard":"","Type":"","Value":"0/56"},{"ChartSign":"1","DateSign":"C","Name":"故障","Standard":"","Type":"","Value":"0/68"},{"ChartSign":"1","DateSign":"C","Name":"节能潜力","Standard":"","Type":"","Value":"0%"}],"Time":"2018-05-25","Weather":"images/weather/104.png"}
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
         * DiagCategory : [{"CategoryName":"能耗计量","Chart":[{"ChartNum":1,"Code":"EC","Text":"电能消耗","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"QH","Text":"冷量消耗","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]}]}]
         * DutyInfo : {}
         * EfficParaInfo : [{"ChartSign":"0","DateSign":"C","Name":"冷冻供","Type":"CoLWT","Value":"11.9℃"},{"ChartSign":"0","DateSign":"C","Name":"冷冻回","Type":"CoEWT","Value":"13.4℃"},{"ChartSign":"0","DateSign":"C","Name":"冷却供","Type":"ChLWT","Value":"34.1℃"},{"ChartSign":"0","DateSign":"C","Name":"冷却回","Type":"ChEWT","Value":"31.9℃"},{"ChartSign":"0","DateSign":"C","Name":"负载率","Type":"PR","Value":"34.8%"}]
         * EnergyParaInfo : [{"ChartSign":"0","DateSign":"C","Name":"电耗(Kwh)","Param":"547.6","Type":"TO","Y_param":"-75%"},{"ChartSign":"0","DateSign":"C","Name":"冷量(Mwh)","Param":"0.7","Type":"QH","Y_param":"-80%"},{"ChartSign":"0","DateSign":"C","Name":"燃气(Nm³)","Param":"0","Type":"GCH","Y_param":"0"},{"ChartSign":"0","DateSign":"C","Name":"室外(℃)","Param":"21","Type":"OW","Y_param":"-3℃"},{"ChartSign":"0","DateSign":"C","Name":"湿球(℃)","Param":"17","Type":"OW","Y_param":"+1℃"},{"ChartSign":"0","DateSign":"C","Name":"室外(%)","Param":"62","Type":"OW","Y_param":"+18%"}]
         * ProjectId : ac17ea95ae5d438b9000efce3fec6ee1
         * ProjectInfo : {"BuildingType":"行政办公建筑","City":"北京市","CoolingArea":"","EndDate":"1542211200","HeatingArea":"116000","ProjectName":"望京UCP(制冷)","ProjectType":"A","StartDate":"1524672000"}
         * RunStatus : 差
         * SysParamInfo : [{"ChartSign":"0","DateSign":"C","Name":"COP(国标)","Standard":"4.2","Type":"LCOP","Value":"1.7"},{"ChartSign":"0","DateSign":"C","Name":"SCOP(国标)","Standard":"4.4","Type":"SCOP","Value":"1.2"},{"ChartSign":"1","DateSign":"C","Name":"巡检评价","Standard":"","Type":"","Value":"0/56"},{"ChartSign":"1","DateSign":"C","Name":"故障","Standard":"","Type":"","Value":"0/68"},{"ChartSign":"1","DateSign":"C","Name":"节能潜力","Standard":"","Type":"","Value":"0%"}]
         * Time : 2018-05-25
         * Weather : images/weather/104.png
         */

        private DutyInfoBean DutyInfo;
        private String ProjectId;
        private ProjectInfoBean ProjectInfo;
        private String RunStatus;
        private String Time;
        private String Weather;
        private List<DiagCategoryBean> DiagCategory;
        private List<EfficParaInfoBean> EfficParaInfo;
        private List<EnergyParaInfoBean> EnergyParaInfo;
        private List<SysParamInfoBean> SysParamInfo;

        public DutyInfoBean getDutyInfo() {
            return DutyInfo;
        }

        public void setDutyInfo(DutyInfoBean DutyInfo) {
            this.DutyInfo = DutyInfo;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public ProjectInfoBean getProjectInfo() {
            return ProjectInfo;
        }

        public void setProjectInfo(ProjectInfoBean ProjectInfo) {
            this.ProjectInfo = ProjectInfo;
        }

        public String getRunStatus() {
            return RunStatus;
        }

        public void setRunStatus(String RunStatus) {
            this.RunStatus = RunStatus;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getWeather() {
            return Weather;
        }

        public void setWeather(String Weather) {
            this.Weather = Weather;
        }

        public List<DiagCategoryBean> getDiagCategory() {
            return DiagCategory;
        }

        public void setDiagCategory(List<DiagCategoryBean> DiagCategory) {
            this.DiagCategory = DiagCategory;
        }

        public List<EfficParaInfoBean> getEfficParaInfo() {
            return EfficParaInfo;
        }

        public void setEfficParaInfo(List<EfficParaInfoBean> EfficParaInfo) {
            this.EfficParaInfo = EfficParaInfo;
        }

        public List<EnergyParaInfoBean> getEnergyParaInfo() {
            return EnergyParaInfo;
        }

        public void setEnergyParaInfo(List<EnergyParaInfoBean> EnergyParaInfo) {
            this.EnergyParaInfo = EnergyParaInfo;
        }

        public List<SysParamInfoBean> getSysParamInfo() {
            return SysParamInfo;
        }

        public void setSysParamInfo(List<SysParamInfoBean> SysParamInfo) {
            this.SysParamInfo = SysParamInfo;
        }

        public static class DutyInfoBean {
        }

        public static class ProjectInfoBean implements Serializable{
            /**
             * BuildingType : 行政办公建筑
             * City : 北京市
             * CoolingArea :
             * EndDate : 1542211200
             * HeatingArea : 116000
             * ProjectName : 望京UCP(制冷)
             * ProjectType : A
             * StartDate : 1524672000
             */

            private String BuildingType;
            private String City;
            private String CoolingArea;
            private String EndDate;
            private String HeatingArea;
            private String ProjectName;
            private String ProjectType;
            private String StartDate;

            public String getBuildingType() {
                return BuildingType;
            }

            public void setBuildingType(String BuildingType) {
                this.BuildingType = BuildingType;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getCoolingArea() {
                return CoolingArea;
            }

            public void setCoolingArea(String CoolingArea) {
                this.CoolingArea = CoolingArea;
            }

            public String getEndDate() {
                return EndDate;
            }

            public void setEndDate(String EndDate) {
                this.EndDate = EndDate;
            }

            public String getHeatingArea() {
                return HeatingArea;
            }

            public void setHeatingArea(String HeatingArea) {
                this.HeatingArea = HeatingArea;
            }

            public String getProjectName() {
                return ProjectName;
            }

            public void setProjectName(String ProjectName) {
                this.ProjectName = ProjectName;
            }

            public String getProjectType() {
                return ProjectType;
            }

            public void setProjectType(String ProjectType) {
                this.ProjectType = ProjectType;
            }

            public String getStartDate() {
                return StartDate;
            }

            public void setStartDate(String StartDate) {
                this.StartDate = StartDate;
            }
        }

        public static class DiagCategoryBean {
            /**
             * CategoryName : 能耗计量
             * Chart : [{"ChartNum":1,"Code":"EC","Text":"电能消耗","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]},{"ChartNum":1,"Code":"QH","Text":"冷量消耗","TimeName":[{"ChartSign":"0","TimeCode":"A","Value":"时"},{"ChartSign":"1","TimeCode":"B","Value":"日"}]}]
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
                 * Code : EC
                 * Text : 电能消耗
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

        public static class EfficParaInfoBean implements Serializable {
            /**
             * ChartSign : 0
             * DateSign : C
             * Name : 冷冻供
             * Type : CoLWT
             * Value : 11.9℃
             */

            private String ChartSign;
            private String DateSign;
            private String Name;
            private String Type;
            private String Value;
            private String DisplayType;
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

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getDisplayType() {
                return DisplayType;
            }

            public void setDisplayType(String displayType) {
                DisplayType = displayType;
            }
        }

        public static class EnergyParaInfoBean implements Serializable{
            /**
             * ChartSign : 0
             * DateSign : C
             * Name : 电耗(Kwh)
             * Param : 547.6
             * Type : TO
             * Y_param : -75%
             */

            private String ChartSign;
            private String DateSign;
            private String Name;
            private String Param;
            private String Type;
            private String Y_param;
            private String DisplayType;
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

            public String getDisplayType() {
                return DisplayType;
            }

            public void setDisplayType(String displayType) {
                DisplayType = displayType;
            }
        }

        public static class SysParamInfoBean implements Serializable{
            /**
             * ChartSign : 0
             * DateSign : C
             * Name : COP(国标)
             * Standard : 4.2
             * Type : LCOP
             * Value : 1.7
             */

            private String ChartSign;
            private String DateSign;
            private String Name;
            private String Standard;
            private String Type;
            private String Value;
            private String DisplayType;
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

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getStandard() {
                return Standard;
            }

            public void setStandard(String Standard) {
                this.Standard = Standard;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getDisplayType() {
                return DisplayType;
            }

            public void setDisplayType(String displayType) {
                DisplayType = displayType;
            }
        }
    }
}
