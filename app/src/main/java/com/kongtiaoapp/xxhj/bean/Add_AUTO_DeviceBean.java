package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/9/8.
 */

public class Add_AUTO_DeviceBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Data":[{"Name":"P","Text":"","Value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7.07,7.07,7.07,7.07,7.1,7.1,7.1,7.1,7.1,7.1,6.97,6.97,6.97,6.97,6.97,6.97,6.97]}],"MaxX":24,"MaxY":15,"MinX":0,"MinY":0,"NowTime":1499838325882,"Time":[0.2,0.4,0.6,0.8,1,1.2,1.4,1.6,1.8,2,2.2,2.4,2.6,2.8,3,3.2,3.4,3.6,3.8,4,4.2,4.4,4.6,4.8,5,5.2,5.4,5.6,5.8,6,6.2,6.4,6.6,6.8,7,7.2,7.4,7.6,7.8,8,8.2,8.4,8.6,8.8,9,9.2,9.4,9.6,9.8,10,10.2,10.4,10.6,10.8,11,11.2,11.4,11.6,11.8,12,12.2,12.4,12.6,12.8,13,13.2,13.4,13.6]}
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
         * Data : [{"Name":"P","Text":"","Value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7.07,7.07,7.07,7.07,7.1,7.1,7.1,7.1,7.1,7.1,6.97,6.97,6.97,6.97,6.97,6.97,6.97]}]
         * MaxX : 24
         * MaxY : 15
         * MinX : 0
         * MinY : 0
         * NowTime : 1499838325882
         * Time : [0.2,0.4,0.6,0.8,1,1.2,1.4,1.6,1.8,2,2.2,2.4,2.6,2.8,3,3.2,3.4,3.6,3.8,4,4.2,4.4,4.6,4.8,5,5.2,5.4,5.6,5.8,6,6.2,6.4,6.6,6.8,7,7.2,7.4,7.6,7.8,8,8.2,8.4,8.6,8.8,9,9.2,9.4,9.6,9.8,10,10.2,10.4,10.6,10.8,11,11.2,11.4,11.6,11.8,12,12.2,12.4,12.6,12.8,13,13.2,13.4,13.6]
         */

        private int MaxX;
        private double MaxY;
        private int MinX;
        private double MinY;
        private long NowTime;
        private List<DataBean> Data;
        private double[] Time;
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

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String flag) {
            Flag = flag;
        }

        public static class DataBean {
            /**
             * Name : P
             * Text :
             * Value : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7.07,7.07,7.07,7.07,7.1,7.1,7.1,7.1,7.1,7.1,6.97,6.97,6.97,6.97,6.97,6.97,6.97]
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
