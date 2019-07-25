package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class EnvironmentInnerBan extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Leader":1,"StatisticsNumber":[{"Type":"OuterTem","DisPatchType":0,"Value":"10.3℃","Name":"室外温度"},{"Type":"OutHum","Value":"21.0℃","DateType":0,"Name":"室外湿度"},{"Type":"OuterTem","Value":"10000KwH","DateType":1,"Name":"电量"},{"Type":"OuterTem","Value":"300m³","DateType":1,"Name":"燃气余量"}],"HenHouse":"7栋","JobCate":[{"Type":1,"Id":"8622C4EBF0F7430098E5709FB0AF5494","HenHouseArray":[{"Id":"a1f34835e84e4cdc9dfc296c929325da","Name":"7栋"}],"Name":"虎什哈第一养鸡厂"},{"Type":0,"Id":"9B5C58ECB9E5413DA7E2884238DB45E2","HenHouseArray":[{"Id":"89ba0f3fba504ce","Name":"4栋"},{"Id":"c863a5a522f44e7","Name":"1栋"},{"Id":"a1f34835e84e4cd","Name":"6栋"},{"Id":"11fae03005f94a4","Name":"2栋"},{"Id":"03e7e38183e14a2","Name":"3栋"},{"Id":"463abca8258f4f6","Name":"5栋"}],"Name":"虎什哈第二养鸡厂"}],"ChartArray":[{"Type":"overview","DateType":3,"Name":"概览"},{"Type":"Tem","DateType":3,"Name":"温度"},{"Type":"Hum","DateType":3,"Name":"湿度"},{"Type":"CO2","DateType":3,"Name":"二氧化碳"}],"GroupData":[{"EnerParam":[{"Type":"","DisplayType":"0","Name":"区域"},{"Type":"","DisplayType":"0","Name":"温度"},{"Type":"","DisplayType":"0","Name":"湿度"},{"Type":"","DisplayType":"0","Name":"二氧化碳"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东区"},{"Type":"","DisplayType":"0","Name":"20.0"},{"Type":"","DisplayType":"0","Name":"32.0"},{"Type":"","DisplayType":"0","Name":"1222.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"中区"},{"Type":"","DisplayType":"0","Name":"19.4"},{"Type":"","DisplayType":"0","Name":"31.4"},{"Type":"","DisplayType":"0","Name":"1244.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西区"},{"Type":"","DisplayType":"0","Name":"17.9"},{"Type":"","DisplayType":"0","Name":"29.5"},{"Type":"","DisplayType":"0","Name":"1008.0"}]},{"EnerParam":[]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"区域"},{"Type":"","DisplayType":"0","Name":"送风"},{"Type":"","DisplayType":"0","Name":"排风"},{"Type":"","DisplayType":"0","Name":"送风频率"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东南"},{"Type":"","DisplayType":"0","Name":"20.4"},{"Type":"","DisplayType":"0","Name":"21.6"},{"Type":"","DisplayType":"0","Name":"3100"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西南"},{"Type":"","DisplayType":"0","Name":"18.4"},{"Type":"","DisplayType":"0","Name":"20.6"},{"Type":"","DisplayType":"0","Name":"2800"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东北"},{"Type":"","DisplayType":"0","Name":"17.8"},{"Type":"","DisplayType":"0","Name":"19.4"},{"Type":"","DisplayType":"0","Name":"3300"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西北"},{"Type":"","DisplayType":"0","Name":"21.4"},{"Type":"","DisplayType":"0","Name":"22.6"},{"Type":"","DisplayType":"0","Name":"2900"}]}]}
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
         * StatisticsNumber : [{"Type":"OuterTem","DisPatchType":0,"Value":"10.3℃","Name":"室外温度"},{"Type":"OutHum","Value":"21.0℃","DateType":0,"Name":"室外湿度"},{"Type":"OuterTem","Value":"10000KwH","DateType":1,"Name":"电量"},{"Type":"OuterTem","Value":"300m³","DateType":1,"Name":"燃气余量"}]
         * HenHouse : 7栋
         * JobCate : [{"Type":1,"Id":"8622C4EBF0F7430098E5709FB0AF5494","HenHouseArray":[{"Id":"a1f34835e84e4cdc9dfc296c929325da","Name":"7栋"}],"Name":"虎什哈第一养鸡厂"},{"Type":0,"Id":"9B5C58ECB9E5413DA7E2884238DB45E2","HenHouseArray":[{"Id":"89ba0f3fba504ce","Name":"4栋"},{"Id":"c863a5a522f44e7","Name":"1栋"},{"Id":"a1f34835e84e4cd","Name":"6栋"},{"Id":"11fae03005f94a4","Name":"2栋"},{"Id":"03e7e38183e14a2","Name":"3栋"},{"Id":"463abca8258f4f6","Name":"5栋"}],"Name":"虎什哈第二养鸡厂"}]
         * ChartArray : [{"Type":"overview","DateType":3,"Name":"概览"},{"Type":"Tem","DateType":3,"Name":"温度"},{"Type":"Hum","DateType":3,"Name":"湿度"},{"Type":"CO2","DateType":3,"Name":"二氧化碳"}]
         * GroupData : [{"EnerParam":[{"Type":"","DisplayType":"0","Name":"区域"},{"Type":"","DisplayType":"0","Name":"温度"},{"Type":"","DisplayType":"0","Name":"湿度"},{"Type":"","DisplayType":"0","Name":"二氧化碳"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东区"},{"Type":"","DisplayType":"0","Name":"20.0"},{"Type":"","DisplayType":"0","Name":"32.0"},{"Type":"","DisplayType":"0","Name":"1222.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"中区"},{"Type":"","DisplayType":"0","Name":"19.4"},{"Type":"","DisplayType":"0","Name":"31.4"},{"Type":"","DisplayType":"0","Name":"1244.0"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西区"},{"Type":"","DisplayType":"0","Name":"17.9"},{"Type":"","DisplayType":"0","Name":"29.5"},{"Type":"","DisplayType":"0","Name":"1008.0"}]},{"EnerParam":[]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"区域"},{"Type":"","DisplayType":"0","Name":"送风"},{"Type":"","DisplayType":"0","Name":"排风"},{"Type":"","DisplayType":"0","Name":"送风频率"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东南"},{"Type":"","DisplayType":"0","Name":"20.4"},{"Type":"","DisplayType":"0","Name":"21.6"},{"Type":"","DisplayType":"0","Name":"3100"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西南"},{"Type":"","DisplayType":"0","Name":"18.4"},{"Type":"","DisplayType":"0","Name":"20.6"},{"Type":"","DisplayType":"0","Name":"2800"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"东北"},{"Type":"","DisplayType":"0","Name":"17.8"},{"Type":"","DisplayType":"0","Name":"19.4"},{"Type":"","DisplayType":"0","Name":"3300"}]},{"EnerParam":[{"Type":"","DisplayType":"0","Name":"西北"},{"Type":"","DisplayType":"0","Name":"21.4"},{"Type":"","DisplayType":"0","Name":"22.6"},{"Type":"","DisplayType":"0","Name":"2900"}]}]
         */

        private int Leader;
        private String HenHouse;
        private List<StatisticsNumberBean> StatisticsNumber;
        private List<JobCateBean> JobCate;
        private List<ChartArrayBean> ChartArray;
        private List<GroupDataBean> GroupData;

        public int getLeader() {
            return Leader;
        }

        public void setLeader(int Leader) {
            this.Leader = Leader;
        }

        public String getHenHouse() {
            return HenHouse;
        }

        public void setHenHouse(String HenHouse) {
            this.HenHouse = HenHouse;
        }

        public List<StatisticsNumberBean> getStatisticsNumber() {
            return StatisticsNumber;
        }

        public void setStatisticsNumber(List<StatisticsNumberBean> StatisticsNumber) {
            this.StatisticsNumber = StatisticsNumber;
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

        public static class StatisticsNumberBean {
            /**
             * Type : OuterTem
             * DisPatchType : 0
             * Value : 10.3℃
             * Name : 室外温度
             * DateType : 0
             */

            private String Type;
            private int DisPatchType;
            private String Value;
            private String Name;
            private int DateType;

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public int getDisPatchType() {
                return DisPatchType;
            }

            public void setDisPatchType(int DisPatchType) {
                this.DisPatchType = DisPatchType;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getDateType() {
                return DateType;
            }

            public void setDateType(int DateType) {
                this.DateType = DateType;
            }
        }

        public static class JobCateBean {
            /**
             * Type : 1
             * Id : 8622C4EBF0F7430098E5709FB0AF5494
             * HenHouseArray : [{"Id":"a1f34835e84e4cdc9dfc296c929325da","Name":"7栋"}]
             * Name : 虎什哈第一养鸡厂
             */

            private int Type;
            private String Id;
            private String Name;
            private List<HenHouseArrayBean> HenHouseArray;

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

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
                 * Id : a1f34835e84e4cdc9dfc296c929325da
                 * Name : 7栋
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
             * Type : overview
             * DateType : 3
             * Name : 概览
             */

            private String Type;
            private int DateType;
            private String Name;
            private String DeviceId;
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

            public String getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(String deviceId) {
                DeviceId = deviceId;
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
                 * Name : 区域
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
