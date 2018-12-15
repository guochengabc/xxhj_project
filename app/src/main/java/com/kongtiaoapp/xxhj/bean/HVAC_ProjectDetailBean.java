package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/29.
 */

public class HVAC_ProjectDetailBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Comment":[],"DiagCategory":[{"Chart":[{"ChartNum":2,"Code":"OW--GCH","Text":"燃气消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"EC","Text":"电能消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"QH","Text":"热量消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}],"CategoryName":"能耗计量"},{"Chart":[{"ChartNum":2,"Code":"OW--BL","Text":"锅炉","TimeName":[{"TimeCode":"A","Value":"时"}]},{"ChartNum":1,"Code":"HP","Text":"泵效率","TimeName":[{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"TD","Text":"供回水","TimeName":[{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}],"CategoryName":"能效分析"}],"DutyInfo":{"UserName":"小溪","Phone":"13701010102"},"ProjectId":"2cbda46200b246f898eefe7325f25ce3","ProjectInfo":{"BuildingType":"商业建筑","City":"北京市","CoolingArea":"","EndDate":"1521043200","HeatingArea":"60692","ProjectName":"核心大楼(供暖)","ProjectType":"B","StartDate":"1509984000"},"RunStatus":"差","RunningInfo":[{"Name":"电","Param":"0Kw","Y_param":"0Kw"},{"Name":"热量","Param":"0GJ","Y_param":"0GJ"},{"Name":"燃气","Param":"0m³","Y_param":"0m³"},{"Name":"供回水","Param":"0.0℃","Y_param":"0.0℃"},{"Name":"室外温度","Param":"0℃","Y_param":"+2℃"},{"Name":"回水温度","Param":"0.0℃","Y_param":""}],"Weather":"images/weather/101.png"}
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
         * Comment : []
         * DiagCategory : [{"Chart":[{"ChartNum":2,"Code":"OW--GCH","Text":"燃气消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"EC","Text":"电能消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"QH","Text":"热量消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}],"CategoryName":"能耗计量"},{"Chart":[{"ChartNum":2,"Code":"OW--BL","Text":"锅炉","TimeName":[{"TimeCode":"A","Value":"时"}]},{"ChartNum":1,"Code":"HP","Text":"泵效率","TimeName":[{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"TD","Text":"供回水","TimeName":[{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}],"CategoryName":"能效分析"}]
         * DutyInfo : {"UserName":"小溪","Phone":"13701010102"}
         * ProjectId : 2cbda46200b246f898eefe7325f25ce3
         * ProjectInfo : {"BuildingType":"商业建筑","City":"北京市","CoolingArea":"","EndDate":"1521043200","HeatingArea":"60692","ProjectName":"核心大楼(供暖)","ProjectType":"B","StartDate":"1509984000"}
         * RunStatus : 差
         * RunningInfo : [{"Name":"电","Param":"0Kw","Y_param":"0Kw"},{"Name":"热量","Param":"0GJ","Y_param":"0GJ"},{"Name":"燃气","Param":"0m³","Y_param":"0m³"},{"Name":"供回水","Param":"0.0℃","Y_param":"0.0℃"},{"Name":"室外温度","Param":"0℃","Y_param":"+2℃"},{"Name":"回水温度","Param":"0.0℃","Y_param":""}]
         * Weather : images/weather/101.png
         */

        private DutyInfoBean DutyInfo;
        private String ProjectId;
        private ProjectInfoBean ProjectInfo;
        private String RunStatus;
        private String Weather;
        private String Time;
        private List<?> Comment;
        private List<DiagCategoryBean> DiagCategory;
        private List<RunningInfoBean> RunningInfo;

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

        public String getWeather() {
            return Weather;
        }

        public void setWeather(String Weather) {
            this.Weather = Weather;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public List<?> getComment() {
            return Comment;
        }

        public void setComment(List<?> Comment) {
            this.Comment = Comment;
        }

        public List<DiagCategoryBean> getDiagCategory() {
            return DiagCategory;
        }

        public void setDiagCategory(List<DiagCategoryBean> DiagCategory) {
            this.DiagCategory = DiagCategory;
        }

        public List<RunningInfoBean> getRunningInfo() {
            return RunningInfo;
        }

        public void setRunningInfo(List<RunningInfoBean> RunningInfo) {
            this.RunningInfo = RunningInfo;
        }

        public static class DutyInfoBean {
            /**
             * UserName : 小溪
             * Phone : 13701010102
             */

            private String UserName;
            private String Phone;

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }
        }

        public static class ProjectInfoBean {
            /**
             * BuildingType : 商业建筑
             * City : 北京市
             * CoolingArea :
             * EndDate : 1521043200
             * HeatingArea : 60692
             * ProjectName : 核心大楼(供暖)
             * ProjectType : B
             * StartDate : 1509984000
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
             * Chart : [{"ChartNum":2,"Code":"OW--GCH","Text":"燃气消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"EC","Text":"电能消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]},{"ChartNum":1,"Code":"QH","Text":"热量消耗","TimeName":[{"TimeCode":"A","Value":"时"},{"TimeCode":"B","Value":"日"},{"TimeCode":"C","Value":"月"}]}]
             * CategoryName : 能耗计量
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
                 * ChartNum : 2
                 * Code : OW--GCH
                 * Text : 燃气消耗
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
                    private String ChartSign;
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

                    public String getChartSign() {
                        return ChartSign;
                    }

                    public void setChartSign(String chartSign) {
                        ChartSign = chartSign;
                    }
                }
            }
        }

        public static class RunningInfoBean {
            /**
             * Name : 电
             * Param : 0Kw
             * Y_param : 0Kw
             */

            private String Name;
            private String Param;
            private String Y_param;

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

            public String getY_param() {
                return Y_param;
            }

            public void setY_param(String Y_param) {
                this.Y_param = Y_param;
            }
        }
    }
}
