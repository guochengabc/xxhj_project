package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/25.  对工程师工单统计
 */

public class EngineerRecordFormBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Engineer":{"Data":[{"Count":275,"DispStateName":"已完成","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[27,15,10,0,0,23,13,8,7,10,0,0,14,16,19,13,18,1,0,17,21,14,14,15]}],"MaxX":31,"MaxY":27,"MinX":1,"MinY":0},"NowTime":1511606868393}
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
         * Engineer : {"Data":[{"Count":275,"DispStateName":"已完成","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[27,15,10,0,0,23,13,8,7,10,0,0,14,16,19,13,18,1,0,17,21,14,14,15]}],"MaxX":31,"MaxY":27,"MinX":1,"MinY":0}
         * NowTime : 1511606868393
         */

        private EngineerBean Engineer;
        private long NowTime;

        public EngineerBean getEngineer() {
            return Engineer;
        }

        public void setEngineer(EngineerBean Engineer) {
            this.Engineer = Engineer;
        }

        public long getNowTime() {
            return NowTime;
        }

        public void setNowTime(long NowTime) {
            this.NowTime = NowTime;
        }

        public static class EngineerBean {
            /**
             * Data : [{"Count":275,"DispStateName":"已完成","Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[27,15,10,0,0,23,13,8,7,10,0,0,14,16,19,13,18,1,0,17,21,14,14,15]}]
             * MaxX : 31
             * MaxY : 27
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

            public void setMinY(double MinY) {
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
                 * Count : 275
                 * DispStateName : 已完成
                 * Time : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]
                 * Values : [27,15,10,0,0,23,13,8,7,10,0,0,14,16,19,13,18,1,0,17,21,14,14,15]
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
    }
}
