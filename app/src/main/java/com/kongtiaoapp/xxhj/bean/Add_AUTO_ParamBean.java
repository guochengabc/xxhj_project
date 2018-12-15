package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/9/11.
 */

public class Add_AUTO_ParamBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"ParamData":[{"Code":"P","Data":"41.3","FieldType":"float","Max":"150","Min":"0","Unit":"kW","Value":"功率"},{"Code":"ECB","Data":"29685.21","FieldType":"float","Max":"","Min":"","Unit":"","Value":"电表表底值"},{"Code":"ECU","Data":"0","FieldType":"float","Max":"","Min":"","Unit":"","Value":"每日用电量"}],"ECData":{"Data":[{"Name":"FirstCoolingPump","Text":"","Value":[0,0,0,0,0,0,0,403.64]}],"MaxX":31,"MaxY":403.64,"MinX":1,"MinY":0,"NowTime":1504838001536,"Time":[1,2,3,4,5,6,7,8]}}
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
         * ParamData : [{"Code":"P","Data":"41.3","FieldType":"float","Max":"150","Min":"0","Unit":"kW","Value":"功率"},{"Code":"ECB","Data":"29685.21","FieldType":"float","Max":"","Min":"","Unit":"","Value":"电表表底值"},{"Code":"ECU","Data":"0","FieldType":"float","Max":"","Min":"","Unit":"","Value":"每日用电量"}]
         * ECData : {"Data":[{"Name":"FirstCoolingPump","Text":"","Value":[0,0,0,0,0,0,0,403.64]}],"MaxX":31,"MaxY":403.64,"MinX":1,"MinY":0,"NowTime":1504838001536,"Time":[1,2,3,4,5,6,7,8]}
         */

        private ECDataBean ECData;
        private List<ParamDataBean> ParamData;

        public ECDataBean getECData() {
            return ECData;
        }

        public void setECData(ECDataBean ECData) {
            this.ECData = ECData;
        }

        public List<ParamDataBean> getParamData() {
            return ParamData;
        }

        public void setParamData(List<ParamDataBean> ParamData) {
            this.ParamData = ParamData;
        }

        public static class ECDataBean {
            /**
             * Data : [{"Name":"FirstCoolingPump","Text":"","Value":[0,0,0,0,0,0,0,403.64]}]
             * MaxX : 31
             * MaxY : 403.64
             * MinX : 1
             * MinY : 0
             * NowTime : 1504838001536
             * Time : [1,2,3,4,5,6,7,8]
             */

            private int MaxX;
            private double MaxY;
            private int MinX;
            private int MinY;
            private long NowTime;
            private List<DataBean> Data;
            private List<Integer> Time;

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

            public int getMinY() {
                return MinY;
            }

            public void setMinY(int MinY) {
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

            public List<Integer> getTime() {
                return Time;
            }

            public void setTime(List<Integer> Time) {
                this.Time = Time;
            }

            public static class DataBean {
                /**
                 * Name : FirstCoolingPump
                 * Text :
                 * Value : [0,0,0,0,0,0,0,403.64]
                 */

                private String Name;
                private String Text;
                private List<Integer> Value;

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

                public List<Integer> getValue() {
                    return Value;
                }

                public void setValue(List<Integer> Value) {
                    this.Value = Value;
                }
            }
        }

        public static class ParamDataBean {
            /**
             * Code : P
             * Data : 41.3
             * FieldType : float
             * Max : 150
             * Min : 0
             * Unit : kW
             * Value : 功率
             */

            private String Code;
            private String Data;
            private String FieldType;
            private String Max;
            private String Min;
            private String Unit;
            private String Value;

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public String getData() {
                return Data;
            }

            public void setData(String Data) {
                this.Data = Data;
            }

            public String getFieldType() {
                return FieldType;
            }

            public void setFieldType(String FieldType) {
                this.FieldType = FieldType;
            }

            public String getMax() {
                return Max;
            }

            public void setMax(String Max) {
                this.Max = Max;
            }

            public String getMin() {
                return Min;
            }

            public void setMin(String Min) {
                this.Min = Min;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }
        }
    }
}
