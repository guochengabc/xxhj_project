package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class RecordDetailsBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"InputType":"1","Value":"当前值","Unit":"m³","Code":"ConsTotal","FieldType":"float","Name":"208145.2350"},{"InputType":"1","Value":"余量","Unit":"m³","Code":"ConsResidue","FieldType":"float","Name":"11840.62"},{"InputType":"1","Value":"上次录入值","Unit":"m³","Code":"LastInput","FieldType":"float","Name":"0"},{"InputType":"1","Value":"昨日用量","Unit":"m³","Code":"LastDay","FieldType":"float","Name":"0"},{"InputType":"1","Value":"昨日余量","Unit":"m³","Code":"LastResidue","FieldType":"float","Name":"0"},{"InputType":"1","Value":"上月用量","Unit":"m³","Code":"LastMonth","FieldType":"float","Name":"0"},{"InputType":"1","Value":"倍率","Unit":"","Code":"Multiply","FieldType":"string","Name":"0"},{"InputType":"1","Value":"对比值","Unit":"","Code":"Percentage","FieldType":"string","Name":"0"},{"InputType":"1","Value":"录入时间","Unit":"","Code":"RecordTime","FieldType":"string","Name":"2018-11-28 19:05:41"},{"InputType":"1","Value":"运行状态","Unit":"","Code":"Status","FieldType":"string","Name":"1"},{"InputType":"1","Value":"时间","Unit":"","Code":"Time","FieldType":"String","Name":"2018-11-28 19:05:41"}]
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
         * InputType : 1
         * Value : 当前值
         * Unit : m³
         * Code : ConsTotal
         * FieldType : float
         * Name : 208145.2350
         */

        private String InputType;
        private String Value;
        private String Unit;
        private String Code;
        private String FieldType;
        private String Name;
    private String Time;
    private String RecordTime;
        public String getInputType() {
            return InputType;
        }

        public void setInputType(String InputType) {
            this.InputType = InputType;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
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

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public String getRecordTime() {
            return RecordTime;
        }

        public void setRecordTime(String recordTime) {
            RecordTime = recordTime;
        }
    }
}
