package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-30.
 * 说明:
 */
public class JieYueList extends RBResponse {


    /**
     * code : 40000
     * resobj : [{"Data":[{"Name":"SECU","Text":"总电耗(Kwh)","Value":[2255.52,2299.87,456.75,814.03,227.95,307.22,305.75,308.22,312.48,318.15,325.06,0,23.64,43.38,63.09,82.8,102.5,122.22,141.94,163.55,189.47,219.52,251.31,243.14,229.49,224.29,225.58,172.14,413.45,345.15,2617.07]}],"MaxX":31,"MaxY":2617.07,"MinX":1,"MinY":0,"NowTime":1496712395048,"Time":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]}]
     */

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * Data : [{"Name":"SECU","Text":"总电耗(Kwh)","Value":[2255.52,2299.87,456.75,814.03,227.95,307.22,305.75,308.22,312.48,318.15,325.06,0,23.64,43.38,63.09,82.8,102.5,122.22,141.94,163.55,189.47,219.52,251.31,243.14,229.49,224.29,225.58,172.14,413.45,345.15,2617.07]}]
         * MaxX : 31
         * MaxY : 2617.07
         * MinX : 1
         * MinY : 0
         * NowTime : 1496712395048
         * Time : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
         */

        private int MaxX;
        private double MaxY;
        private int MinX;
        private double MinY;
        private long NowTime;
        private List<DataBean> Data;
        private double[] Time;

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

        public long getNowTime() {
            return NowTime;
        }

        public void setNowTime(long NowTime) {
            this.NowTime = NowTime;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public double[] getTime() {
            return Time;
        }

        public void setTime(double[] Time) {
            this.Time = Time;
        }

        public static class DataBean {
            /**
             * Name : SECU
             * Text : 总电耗(Kwh)
             * Value : [2255.52,2299.87,456.75,814.03,227.95,307.22,305.75,308.22,312.48,318.15,325.06,0,23.64,43.38,63.09,82.8,102.5,122.22,141.94,163.55,189.47,219.52,251.31,243.14,229.49,224.29,225.58,172.14,413.45,345.15,2617.07]
             */

            private String Name;
            private String Text;
            private double[] Value;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }

            public double[] getValue() {
                return Value;
            }

            public void setValue(double[] Value) {
                this.Value = Value;
            }
        }
    }
}
