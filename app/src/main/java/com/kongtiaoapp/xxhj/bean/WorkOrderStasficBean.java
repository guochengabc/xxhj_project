package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/22.
 */

public class WorkOrderStasficBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Engineer":{"Data":[{"Count":16,"DispStateName":"已发单","NowTime":"2017-11-24","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16]}],"MaxX":31,"MaxY":16,"MinX":1,"MinY":0},"Repair":[{"Count":11,"DispStateName":"报修","MaxX":31,"MaxY":11,"MinX":1,"MinY":0,"NowTime":"2017-11-24","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11]}],"WorkData":{"Total":11,"Data":[{"Count":7,"DispState":7,"DispStateName":"结束"},{"Count":16,"DispState":2,"DispStateName":"已接单"},{"Count":15,"DispState":5,"DispStateName":"已完成"},{"Count":11,"DispState":0,"DispStateName":"报修"}]}}
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
         * Engineer : {"Data":[{"Count":16,"DispStateName":"已发单","NowTime":"2017-11-24","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16]}],"MaxX":31,"MaxY":16,"MinX":1,"MinY":0}
         * Repair : [{"Count":11,"DispStateName":"报修","MaxX":31,"MaxY":11,"MinX":1,"MinY":0,"NowTime":"2017-11-24","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11]}]
         * WorkData : {"Total":11,"Data":[{"Count":7,"DispState":7,"DispStateName":"结束"},{"Count":16,"DispState":2,"DispStateName":"已接单"},{"Count":15,"DispState":5,"DispStateName":"已完成"},{"Count":11,"DispState":0,"DispStateName":"报修"}]}
         */

        private EngineerBean Engineer;
        private WorkDataBean WorkData;
        private List<RepairBean> Repair;
        private long NowTime;

        public EngineerBean getEngineer() {
            return Engineer;
        }

        public void setEngineer(EngineerBean Engineer) {
            this.Engineer = Engineer;
        }

        public WorkDataBean getWorkData() {
            return WorkData;
        }

        public void setWorkData(WorkDataBean WorkData) {
            this.WorkData = WorkData;
        }

        public List<RepairBean> getRepair() {
            return Repair;
        }

        public void setRepair(List<RepairBean> Repair) {
            this.Repair = Repair;
        }

        public long getNowTime() {
            return NowTime;
        }

        public void setNowTime(long nowTime) {
            NowTime = nowTime;
        }

        public static class EngineerBean {
            /**
             * Data : [{"Count":16,"DispStateName":"已发单","NowTime":"2017-11-24","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16]}]
             * MaxX : 31
             * MaxY : 16
             * MinX : 1
             * MinY : 0
             */

            private int MaxX;
            private double MaxY;
            private int MinX;
            private double MinY;
            private List<DataBean> Data;

            public int getMaxX() {
                return MaxX;
            }

            public void setMaxX(int MaxX) {
                this.MaxX = MaxX;
            }

            public double getMaxY() {
                return MaxY;
            }

            public void setMaxY(double MaxY) {
                this.MaxY = MaxY;
            }

            public int getMinX() {
                return MinX;
            }

            public void setMinX(int MinX) {
                this.MinX = MinX;
            }

            public double getMinY() {
                return MinY;
            }

            public void setMinY(int MinY) {
                this.MinY = MinY;
            }

            public List<DataBean> getData() {
                return Data;
            }

            public void setData(List<DataBean> Data) {
                this.Data = Data;
            }

            public static class DataBean {
                /**
                 * Count : 16
                 * DispStateName : 已发单
                 * NowTime : 2017-11-24
                 * Time : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]
                 * Values : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16]
                 */

                private int Count;
                private String DispStateName;
                private double[] Time;
                private double[] Values;

                public int getCount() {
                    return Count;
                }

                public void setCount(int Count) {
                    this.Count = Count;
                }

                public String getDispStateName() {
                    return DispStateName;
                }

                public void setDispStateName(String DispStateName) {
                    this.DispStateName = DispStateName;
                }


                public double[] getTime() {
                    return Time;
                }

                public void setTime(double[] Time) {
                    this.Time = Time;
                }

                public double[] getValues() {
                    return Values;
                }

                public void setValues(double[] Values) {
                    this.Values = Values;
                }
            }
        }

        public static class WorkDataBean {
            /**
             * Total : 11
             * Data : [{"Count":7,"DispState":7,"DispStateName":"结束"},{"Count":16,"DispState":2,"DispStateName":"已接单"},{"Count":15,"DispState":5,"DispStateName":"已完成"},{"Count":11,"DispState":0,"DispStateName":"报修"}]
             */

            private int Total;
            private List<DataBeanX> Data;

            public int getTotal() {
                return Total;
            }

            public void setTotal(int Total) {
                this.Total = Total;
            }

            public List<DataBeanX> getData() {
                return Data;
            }

            public void setData(List<DataBeanX> Data) {
                this.Data = Data;
            }

            public static class DataBeanX {
                /**
                 * Count : 7
                 * DispState : 7
                 * DispStateName : 结束
                 */

                private int Count;
                private int DispState;
                private String DispStateName;

                public int getCount() {
                    return Count;
                }

                public void setCount(int Count) {
                    this.Count = Count;
                }

                public int getDispState() {
                    return DispState;
                }

                public void setDispState(int DispState) {
                    this.DispState = DispState;
                }

                public String getDispStateName() {
                    return DispStateName;
                }

                public void setDispStateName(String DispStateName) {
                    this.DispStateName = DispStateName;
                }
            }
        }

        public static class RepairBean {
            /**
             * Count : 11
             * DispStateName : 报修
             * MaxX : 31
             * MaxY : 11
             * MinX : 1
             * MinY : 0
             * NowTime : 2017-11-24
             * Time : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]
             * Values : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11]
             */

            private int Count;
            private String DispStateName;
            private int MaxX;
            private double MaxY;
            private int MinX;
            private double MinY;
            private String NowTime;
            private  double[] Time;
            private  double[] Values;

            public int getCount() {
                return Count;
            }

            public void setCount(int Count) {
                this.Count = Count;
            }

            public String getDispStateName() {
                return DispStateName;
            }

            public void setDispStateName(String DispStateName) {
                this.DispStateName = DispStateName;
            }

            public int getMaxX() {
                return MaxX;
            }

            public void setMaxX(int MaxX) {
                this.MaxX = MaxX;
            }

            public double getMaxY() {
                return MaxY;
            }

            public void setMaxY(double MaxY) {
                this.MaxY = MaxY;
            }

            public int getMinX() {
                return MinX;
            }

            public void setMinX(int MinX) {
                this.MinX = MinX;
            }

            public double getMinY() {
                return MinY;
            }

            public void setMinY(double MinY) {
                this.MinY = MinY;
            }

            public String getNowTime() {
                return NowTime;
            }

            public void setNowTime(String NowTime) {
                this.NowTime = NowTime;
            }

            public double[] getTime() {
                return Time;
            }

            public void setTime( double[] Time) {
                this.Time = Time;
            }

            public double[] getValues() {
                return Values;
            }

            public void setValues( double[] Values) {
                this.Values = Values;
            }
        }
    }
}
