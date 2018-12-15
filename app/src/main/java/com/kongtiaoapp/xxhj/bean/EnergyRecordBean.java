package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/7/18.
 */

public class EnergyRecordBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Electricity":[{"Code":"ConsTotal","FieldType":"float","InputType":"0","Unit":"kWh","Value":"当前值"},{"Code":"ThisMonth","FieldType":"float","InputType":"1","Unit":"kWh","Value":"本月值"},{"Code":"LastMonth","FieldType":"float","InputType":"1","Unit":"kWh","Value":"上月值"},{"Code":"Percentage","FieldType":"string","InputType":"1","Unit":"","Value":"对比值"},{"Code":"Status","FieldType":"string","InputType":"1","Unit":"","Value":"运行状态"}],"Title":"表单录入"}
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
         * Electricity : [{"Code":"ConsTotal","FieldType":"float","InputType":"0","Unit":"kWh","Value":"当前值"},{"Code":"ThisMonth","FieldType":"float","InputType":"1","Unit":"kWh","Value":"本月值"},{"Code":"LastMonth","FieldType":"float","InputType":"1","Unit":"kWh","Value":"上月值"},{"Code":"Percentage","FieldType":"string","InputType":"1","Unit":"","Value":"对比值"},{"Code":"Status","FieldType":"string","InputType":"1","Unit":"","Value":"运行状态"}]
         * Title : 表单录入
         */

        private String Title;
        private String SensorId;
        private String ReadModel;
        private List<ElectricityBean> Electricity;
        private String SensorType;

        public String getSensorType() {
            return SensorType;
        }

        public void setSensorType(String sensorType) {
            SensorType = sensorType;
        }

        public String getReadModel() {
            return ReadModel;
        }

        public void setReadModel(String ReadModel) {
            this.ReadModel = ReadModel;
        }

        public String getSensorId() {
            return SensorId;
        }

        public void setSensorId(String sensorId) {
            SensorId = sensorId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public List<ElectricityBean> getElectricity() {
            return Electricity;
        }

        public void setElectricity(List<ElectricityBean> Electricity) {
            this.Electricity = Electricity;
        }

        public static class ElectricityBean {
            /**
             * Code : ConsTotal
             * FieldType : float
             * InputType : 0
             * Unit : kWh
             * Value : 当前值
             */

            private String Code;
            private String FieldType;
            private String InputType;
            private String Unit;
            private String Value;
            private String Name;
            private String Time;
            private String RecordTime;

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

            public String getInputType() {
                return InputType;
            }

            public void setInputType(String InputType) {
                this.InputType = InputType;
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

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
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
}
