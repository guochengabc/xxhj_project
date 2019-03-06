package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class EnvironmentInnerBan extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Leader":2,"JobCate":[{"Id":"1","Name":"鸡场1","HenHouseArray":[{"Id":"11","Name":"鸡舍1"},{"Id":"12","Name":"鸡舍2"},{"Id":"13","Name":"鸡舍3"},{"Id":"14","Name":"鸡舍4"},{"Id":"15","Name":"鸡舍5"},{"Id":"16","Name":"鸡舍6"}]},{"Id":"2","Name":"鸡场2","HenHouseArray":[{"Id":"21","Name":"鸡舍1"},{"Id":"22","Name":"鸡舍2"},{"Id":"23","Name":"鸡舍3"},{"Id":"24","Name":"鸡舍4"},{"Id":"25","Name":"鸡舍5"},{"Id":"26","Name":"鸡舍6"}]}],"ChartCate":{"ChartArray":[{"Name":"温度","Type":"Tem","DateType":1},{"Name":"湿度","Type":"Hum","DateType":2},{"Name":"二氧化碳","Type":"CO2","DateType":3}]},"GroupData":[{"EnerParam":[{"DisplayType":"O","Name":"小区","Type":""},{"DisplayType":"O","Name":"温度","Type":""},{"DisplayType":"O","Name":"湿度","Type":""},{"DisplayType":"O","Name":"二氧化碳","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"东区","Type":""},{"DisplayType":"O","Name":"12℃","Type":""},{"DisplayType":"O","Name":"60","Type":""},{"DisplayType":"O","Name":"1800","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"中区","Type":""},{"DisplayType":"O","Name":"13℃","Type":""},{"DisplayType":"O","Name":"72","Type":""},{"DisplayType":"O","Name":"1970","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"西区","Type":""},{"DisplayType":"O","Name":"13.6℃","Type":""},{"DisplayType":"O","Name":"75","Type":""},{"DisplayType":"O","Name":"2106","Type":""}]}]}
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
         * Leader : 2
         * JobCate : [{"Id":"1","Name":"鸡场1","HenHouseArray":[{"Id":"11","Name":"鸡舍1"},{"Id":"12","Name":"鸡舍2"},{"Id":"13","Name":"鸡舍3"},{"Id":"14","Name":"鸡舍4"},{"Id":"15","Name":"鸡舍5"},{"Id":"16","Name":"鸡舍6"}]},{"Id":"2","Name":"鸡场2","HenHouseArray":[{"Id":"21","Name":"鸡舍1"},{"Id":"22","Name":"鸡舍2"},{"Id":"23","Name":"鸡舍3"},{"Id":"24","Name":"鸡舍4"},{"Id":"25","Name":"鸡舍5"},{"Id":"26","Name":"鸡舍6"}]}]
         * ChartCate : {"ChartArray":[{"Name":"温度","Type":"Tem","DateType":1},{"Name":"湿度","Type":"Hum","DateType":2},{"Name":"二氧化碳","Type":"CO2","DateType":3}]}
         * GroupData : [{"EnerParam":[{"DisplayType":"O","Name":"小区","Type":""},{"DisplayType":"O","Name":"温度","Type":""},{"DisplayType":"O","Name":"湿度","Type":""},{"DisplayType":"O","Name":"二氧化碳","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"东区","Type":""},{"DisplayType":"O","Name":"12℃","Type":""},{"DisplayType":"O","Name":"60","Type":""},{"DisplayType":"O","Name":"1800","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"中区","Type":""},{"DisplayType":"O","Name":"13℃","Type":""},{"DisplayType":"O","Name":"72","Type":""},{"DisplayType":"O","Name":"1970","Type":""}]},{"EnerParam":[{"DisplayType":"O","Name":"西区","Type":""},{"DisplayType":"O","Name":"13.6℃","Type":""},{"DisplayType":"O","Name":"75","Type":""},{"DisplayType":"O","Name":"2106","Type":""}]}]
         */

        private int Leader;
        private ChartCateBean ChartCate;
        private List<JobCateBean> JobCate;
        private List<GroupDataBean> GroupData;

        public int getLeader() {
            return Leader;
        }

        public void setLeader(int Leader) {
            this.Leader = Leader;
        }

        public ChartCateBean getChartCate() {
            return ChartCate;
        }

        public void setChartCate(ChartCateBean ChartCate) {
            this.ChartCate = ChartCate;
        }

        public List<JobCateBean> getJobCate() {
            return JobCate;
        }

        public void setJobCate(List<JobCateBean> JobCate) {
            this.JobCate = JobCate;
        }

        public List<GroupDataBean> getGroupData() {
            return GroupData;
        }

        public void setGroupData(List<GroupDataBean> GroupData) {
            this.GroupData = GroupData;
        }

        public static class ChartCateBean {
            private List<ChartArrayBean> ChartArray;

            public List<ChartArrayBean> getChartArray() {
                return ChartArray;
            }

            public void setChartArray(List<ChartArrayBean> ChartArray) {
                this.ChartArray = ChartArray;
            }

            public static class ChartArrayBean {
                /**
                 * Name : 温度
                 * Type : Tem
                 * DateType : 1
                 */

                private String Name;
                private String Type;
                private int DateType;

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

                public int getDateType() {
                    return DateType;
                }

                public void setDateType(int DateType) {
                    this.DateType = DateType;
                }
            }
        }

        public static class JobCateBean {
            /**
             * Id : 1
             * Name : 鸡场1
             * HenHouseArray : [{"Id":"11","Name":"鸡舍1"},{"Id":"12","Name":"鸡舍2"},{"Id":"13","Name":"鸡舍3"},{"Id":"14","Name":"鸡舍4"},{"Id":"15","Name":"鸡舍5"},{"Id":"16","Name":"鸡舍6"}]
             */

            private String Id;
            private String Name;
            private List<HenHouseArrayBean> HenHouseArray;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public List<HenHouseArrayBean> getHenHouseArray() {
                return HenHouseArray;
            }

            public void setHenHouseArray(List<HenHouseArrayBean> HenHouseArray) {
                this.HenHouseArray = HenHouseArray;
            }

            public static class HenHouseArrayBean {
                /**
                 * Id : 11
                 * Name : 鸡舍1
                 */

                private String Id;
                private String Name;

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
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
