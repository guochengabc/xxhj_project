package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/25.
 */

public class RepairFormSecondBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"NowTime":1509465600000,"Repair":[{"Count":287,"DispStateName":"报修","MaxX":31,"MaxY":26,"MinX":1,"MinY":0,"Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[26,18,8,0,0,22,18,6,10,15,1,0,13,20,18,16,12,1,0,21,14,19,18,11]}]}
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
         * NowTime : 1509465600000
         * Repair : [{"Count":287,"DispStateName":"报修","MaxX":31,"MaxY":26,"MinX":1,"MinY":0,"Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],"Values":[26,18,8,0,0,22,18,6,10,15,1,0,13,20,18,16,12,1,0,21,14,19,18,11]}]
         */

        private long NowTime;
        private List<RepairBean> Repair;

        public long getNowTime() {
            return NowTime;
        }

        public void setNowTime(long NowTime) {
            this.NowTime = NowTime;
        }

        public List<RepairBean> getRepair() {
            return Repair;
        }

        public void setRepair(List<RepairBean> Repair) {
            this.Repair = Repair;
        }

        public static class RepairBean {
            /**
             * Count : 287
             * DispStateName : 报修
             * MaxX : 31
             * MaxY : 26
             * MinX : 1
             * MinY : 0
             * Time : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]
             * Values : [26,18,8,0,0,22,18,6,10,15,1,0,13,20,18,16,12,1,0,21,14,19,18,11]
             */

            private int Count;
            private String DispStateName;
            private int MaxX;
            private double MaxY;
            private int MinX;
            private double MinY;
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
