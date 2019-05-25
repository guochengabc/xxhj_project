package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2016/11/12.
 */
public class ChartDataBean extends RBResponse {


    /**
     * resobj : {"Data":[{"Name":"RLRatio","Text":"运行负载率","Value":[100,100,100,100,100,120,100,1]},{"Name":"DLRatio","Text":"设计负载率","Value":[100,100,100,100,100,120,100,1]},{"Name":"Aload","Text":"实际单位负荷","Value":[100,100,100,100,100,120,100,1]}],"Time":[10,10.2,10.4,10.6,10.8,11,11.2,11.4],"MaxY":120,"MaxX":24}
     * code : 40000
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
         * Data : [{"Name":"RLRatio","Text":"运行负载率","Value":[100,100,100,100,100,120,100,1]},{"Name":"DLRatio","Text":"设计负载率","Value":[100,100,100,100,100,120,100,1]},{"Name":"Aload","Text":"实际单位负荷","Value":[100,100,100,100,100,120,100,1]}]
         * Time : [10,10.2,10.4,10.6,10.8,11,11.2,11.4]
         * MaxY : 120
         * MaxX : 24
         */
        private String Flag;
        private double MaxY;
        private int MaxX;
        private double MinY;
        private List<DataBean> Data;
        private double[] Time;
        private long NowTime;

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String flag) {
            Flag = flag;
        }

        public double getMaxY() {
            return MaxY;
        }

        public double getMinY() {
            return MinY;
        }

        public void setMinY(double minY) {
            MinY = minY;
        }

        public void setMaxY(double MaxY) {
            this.MaxY = MaxY;
        }

        public int getMaxX() {
            return MaxX;
        }

        public void setMaxX(int MaxX) {
            this.MaxX = MaxX;
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

        public long getNowTime() {
            return NowTime;
        }

        public void setNowTime(long nowTime) {
            NowTime = nowTime;
        }

        public static class DataBean {
            /**
             * Name : RLRatio
             * Text : 运行负载率
             * Value : [100,100,100,100,100,120,100,1]
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
