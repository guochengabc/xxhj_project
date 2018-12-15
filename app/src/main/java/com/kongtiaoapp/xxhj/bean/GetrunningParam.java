package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2016/12/28.
 */
public class GetrunningParam extends RBResponse {

    /**
     * resobj : {"ChillingTower":[{"Value":"供水温度","FieldType":"float","Code":"LeavingWaterTemp","Unit":"℃"},{"Value":"回水温度","FieldType":"float","Code":"EnteringWaterTemp","Unit":"℃"},{"Value":"供水压力","FieldType":"float","Code":"LeavingWaterPres","Unit":"KPa"},{"Value":"回水压力","FieldType":"float","Code":"EnteringWaterPres","Unit":"KPa"},{"Value":"电流","FieldType":"float","Code":"Current","Unit":"A"},{"Value":"电压","FieldType":"float","Code":"Voltage","Unit":"V"},{"Value":"功率","FieldType":"float","Code":"Power","Unit":"Kw"}]}
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
        private List<RunningParam> ChillingTower;

        public List<RunningParam> getChillingTower() {
            return ChillingTower;
        }

        public void setChillingTower(List<RunningParam> ChillingTower) {
            this.ChillingTower = ChillingTower;
        }

        public static class RunningParam {
            /**
             * Value : 供水温度
             * FieldType : float
             * Code : LeavingWaterTemp
             * Unit : ℃
             */

            private String Value;
            private String FieldType;
            private String Code;
            private String Unit;
            private String myContent;
            private String Data;
            private String Min;
            private String Max;
            public String getMyContent() {
                return myContent;
            }

            public void setMyContent(String myContent) {
                this.myContent = myContent;
            }
            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getFieldType() {
                return FieldType;
            }

            public void setFieldType(String FieldType) {
                this.FieldType = FieldType;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public String getData() {
                return Data;
            }

            public void setData(String data) {
                Data = data;
            }

            public String getMin() {
                return Min;
            }

            public void setMin(String min) {
                Min = min;
            }

            public String getMax() {
                return Max;
            }

            public void setMax(String max) {
                Max = max;
            }
        }
    }
}
