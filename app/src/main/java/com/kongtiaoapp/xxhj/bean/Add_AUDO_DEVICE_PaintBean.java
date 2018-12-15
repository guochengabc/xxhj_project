package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/9/11.
 */

public class Add_AUDO_DEVICE_PaintBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Data":[{"Name":"FirstCoolingPump","Text":"","Value":[0,0,0,0,0,0,0,976.57,982.94,976.09,534.66]}],"MaxX":31,"MaxY":982.94,"MinX":1,"MinY":0,"NowTime":1505108945724,"Time":[1,2,3,4,5,6,7,8,9,10,11]}
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
         * Data : [{"Name":"FirstCoolingPump","Text":"","Value":[0,0,0,0,0,0,0,976.57,982.94,976.09,534.66]}]
         * MaxX : 31
         * MaxY : 982.94
         * MinX : 1
         * MinY : 0
         * NowTime : 1505108945724
         * Time : [1,2,3,4,5,6,7,8,9,10,11]
         */

        private int MaxX;
        private double MaxY;
        private int MinX;
        private double MinY;
        private long NowTime;
        private List<DataBean> Data;
        private  double[]  Time;
        private String Flag;
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

        public  double getMinY() {
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

        public void setTime( double[]  Time) {
            this.Time = Time;
        }

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String flag) {
            Flag = flag;
        }

        public static class DataBean {
            /**
             * Name : FirstCoolingPump
             * Text :
             * Value : [0,0,0,0,0,0,0,976.57,982.94,976.09,534.66]
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
