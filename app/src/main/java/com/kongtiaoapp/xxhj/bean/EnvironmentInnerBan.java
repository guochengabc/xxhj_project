package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class EnvironmentInnerBan extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Leader":1,"OuterTem":"18℃","OutHum":"30.4","JobCate":[{"Id":1,"HenHouseArray":[{"Id":"21","Name":"鸡舍1"},{"Id":"22","Name":"鸡舍2"},{"Id":"23","Name":"鸡舍3"}],"Name":"鸡场1"},{"Id":2,"HenHouseArray":[{"Id":"24","Name":"鸡舍4"},{"Id":"25","Name":"鸡舍5"}],"Name":"鸡场2"}],"ChartArray":[{"Type":"Tem","DateType":1,"Name":"温度"},{"Type":"Hum","DateType":2,"Name":"湿度"},{"Type":"CO2","DateType":3,"Name":"二氧化碳"}],"GroupData":[{"EnerParam":[{"Type":"","DisplayType":"0","Name":"位置"},{"Type":"","DisplayType":"0","Name":"温度"},{"Type":"","DisplayType":"0","Name":"湿度"},{"Type":"","DisplayType":"0","Name":"二氧化碳"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东区"},{"Type":"","DisplayType":"0","Name":"18.0"},{"Type":"","DisplayType":"0","Name":"33.0"},{"Type":"","DisplayType":"0","Name":"1234.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"中区"},{"Type":"","DisplayType":"0","Name":"18.4"},{"Type":"","DisplayType":"0","Name":"30.4"},{"Type":"","DisplayType":"0","Name":"1214.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西区"},{"Type":"","DisplayType":"0","Name":"16.9"},{"Type":"","DisplayType":"0","Name":"27.5"},{"Type":"","DisplayType":"0","Name":"1068.0"}]}]}
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
         * Leader : 1
         * OuterTem : 18℃
         * OutHum : 30.4
         * JobCate : [{"Id":1,"HenHouseArray":[{"Id":"21","Name":"鸡舍1"},{"Id":"22","Name":"鸡舍2"},{"Id":"23","Name":"鸡舍3"}],"Name":"鸡场1"},{"Id":2,"HenHouseArray":[{"Id":"24","Name":"鸡舍4"},{"Id":"25","Name":"鸡舍5"}],"Name":"鸡场2"}]
         * ChartArray : [{"Type":"Tem","DateType":1,"Name":"温度"},{"Type":"Hum","DateType":2,"Name":"湿度"},{"Type":"CO2","DateType":3,"Name":"二氧化碳"}]
         * GroupData : [{"EnerParam":[{"Type":"","DisplayType":"0","Name":"位置"},{"Type":"","DisplayType":"0","Name":"温度"},{"Type":"","DisplayType":"0","Name":"湿度"},{"Type":"","DisplayType":"0","Name":"二氧化碳"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东区"},{"Type":"","DisplayType":"0","Name":"18.0"},{"Type":"","DisplayType":"0","Name":"33.0"},{"Type":"","DisplayType":"0","Name":"1234.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"中区"},{"Type":"","DisplayType":"0","Name":"18.4"},{"Type":"","DisplayType":"0","Name":"30.4"},{"Type":"","DisplayType":"0","Name":"1214.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西区"},{"Type":"","DisplayType":"0","Name":"16.9"},{"Type":"","DisplayType":"0","Name":"27.5"},{"Type":"","DisplayType":"0","Name":"1068.0"}]}]
         */

        private int Leader;
        private String OuterTem;
        private String OutHum;
        private List<JobCateBean> JobCate;
        private List<ChartArrayBean> ChartArray;
        private List<GroupDataBean> GroupData;

        public int getLeader() {
            return Leader;
        }

        public void setLeader(int Leader) {
            this.Leader = Leader;
        }

        public String getOuterTem() {
            return OuterTem;
        }

        public void setOuterTem(String OuterTem) {
            this.OuterTem = OuterTem;
        }

        public String getOutHum() {
            return OutHum;
        }

        public void setOutHum(String OutHum) {
            this.OutHum = OutHum;
        }

        public List<JobCateBean> getJobCate() {
            return JobCate;
        }

        public void setJobCate(List<JobCateBean> JobCate) {
            this.JobCate = JobCate;
        }

        public List<ChartArrayBean> getChartArray() {
            return ChartArray;
        }

        public void setChartArray(List<ChartArrayBean> ChartArray) {
            this.ChartArray = ChartArray;
        }

        public List<GroupDataBean> getGroupData() {
            return GroupData;
        }

        public void setGroupData(List<GroupDataBean> GroupData) {
            this.GroupData = GroupData;
        }

        public static class JobCateBean {
            /**
             * Id : 1
             * HenHouseArray : [{"Id":"21","Name":"鸡舍1"},{"Id":"22","Name":"鸡舍2"},{"Id":"23","Name":"鸡舍3"}]
             * Name : 鸡场1
             */

            private int Id;
            private String Name;
            private List<HenHouseArrayBean> HenHouseArray;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
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
                 * Id : 21
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

        public static class ChartArrayBean {
            /**
             * Type : Tem
             * DateType : 1
             * Name : 温度
             */

            private String Type;
            private int DateType;
            private String Name;

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

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
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
                 * Type :
                 * DisplayType : 0
                 * Name : 位置
                 */

                private String Type;
                private String DisplayType;
                private String Name;

                public String getType() {
                    return Type;
                }

                public void setType(String Type) {
                    this.Type = Type;
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
            }
        }
    }
}
