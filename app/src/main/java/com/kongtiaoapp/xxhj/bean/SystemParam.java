package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class SystemParam extends RBResponse {

    /**
     * code : 40000
     * resobj : {"System":[{"Code":"ChLWT","FieldType":"float","Max":"40","Min":"15","Unit":"℃","Value":"冷却水供水温度"},{"Code":"ChEWT","FieldType":"float","Max":"40","Min":"15","Unit":"℃","Value":"冷却水回水温度"},{"Code":"ChLWP","FieldType":"float","Max":"1000","Min":"0","Unit":"KPa","Value":"冷却水供水压力"},{"Code":"ChEWP","FieldType":"float","Max":"1000","Min":"0","Unit":"KPa","Value":"冷却水回水压力"},{"Code":"ChF","FieldType":"float","Max":"1000","Min":"0","Unit":"m3/h","Value":"冷却水流量"},{"Code":"TCA","FieldType":"float","Max":"1000000","Min":"0","Unit":"㎡","Value":"制冷总面积"},{"Code":"InT","FieldType":"float","Max":"35","Min":"0","Unit":"℃","Value":"室内温度"},{"Code":"OutT","FieldType":"float","Max":"40","Min":"-35","Unit":"℃","Value":"室外温度"},{"Code":"EC","FieldType":"float","Max":"","Min":"","Unit":"kWh","Value":"能耗"},{"Code":"WT","FieldType":"float","Max":"","Min":"","Unit":"℃","Value":"湿球温度"},{"Code":"ChS","FieldType":"float","Max":"","Min":"","Unit":"m3","Value":"冷却水排污量"},{"Code":"CoS","FieldType":"float","Max":"","Min":"","Unit":"m3","Value":"冷冻水排污量"},{"Code":"Cond","FieldType":"float","Max":"","Min":"","Unit":"S/m","Value":"电导率"},{"Code":"SplitLine","FieldType":"","Max":"","Min":"","Unit":"","Value":""}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<SystemBean> System;

        public List<SystemBean> getSystem() {
            return System;
        }

        public void setSystem(List<SystemBean> System) {
            this.System = System;
        }

        public static class SystemBean {
            /**
             * Code : ChLWT
             * FieldType : float
             * Max : 40
             * Min : 15
             * Unit : ℃
             * Value : 冷却水供水温度
             */

            private String Code;
            private String FieldType;
            private String Max;
            private String Min;
            private String Unit;
            private String Value;
            private String myContent;

            public String getMyContent() {
                return myContent;
            }

            public void setMyContent(String myContent) {
                this.myContent = myContent;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
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
