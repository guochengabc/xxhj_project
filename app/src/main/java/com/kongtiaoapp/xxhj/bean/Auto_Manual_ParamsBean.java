package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2016/12/29.
 */
public class Auto_Manual_ParamsBean extends RBResponse {

    /**
     * resobj : {"DeviceType":"Boiler","TotalCount":3,"DeviceId":"4a3e73e50fb34a82894bed7b208162b8","Param":[{"Min":"1","Max":"10000","Value":"燃气余量","Unit":"m3","Code":"GasB","FieldType":"float"},{"Min":"1","Max":"10000","Value":"燃气日用量","Unit":"m3","Code":"GasU","FieldType":"float"},{"Min":"1","Max":"10000","Value":"燃气充气量","Unit":"m3","Code":"GasF","FieldType":"float"}],"DeviceName":"1号锅炉"}
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
         * DeviceType : Boiler
         * TotalCount : 3
         * DeviceId : 4a3e73e50fb34a82894bed7b208162b8
         * Param : [{"Min":"1","Max":"10000","Value":"燃气余量","Unit":"m3","Code":"GasB","FieldType":"float"},{"Min":"1","Max":"10000","Value":"燃气日用量","Unit":"m3","Code":"GasU","FieldType":"float"},{"Min":"1","Max":"10000","Value":"燃气充气量","Unit":"m3","Code":"GasF","FieldType":"float"}]
         * DeviceName : 1号锅炉
         */

        private String DeviceType;
        private int TotalCount;
        private String DeviceId;
        private String DeviceName;
        private List<ParamBean> Param;

        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String DeviceType) {
            this.DeviceType = DeviceType;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public String getDeviceId() {
            return DeviceId;
        }

        public void setDeviceId(String DeviceId) {
            this.DeviceId = DeviceId;
        }

        public String getDeviceName() {
            return DeviceName;
        }

        public void setDeviceName(String DeviceName) {
            this.DeviceName = DeviceName;
        }

        public List<ParamBean> getParam() {
            return Param;
        }

        public void setParam(List<ParamBean> Param) {
            this.Param = Param;
        }

        public static class ParamBean {
            /**
             * Min : 1
             * Max : 10000
             * Value : 燃气余量
             * Unit : m3
             * Code : GasB
             * FieldType : float
             */

            private String Min;
            private String Max;
            private String Value;
            private String Unit;
            private String Code;
            private String FieldType;
            private String myContent;

            public String getMyContent() {
                return myContent;
            }

            public void setMyContent(String myContent) {
                this.myContent = myContent;
            }

            public String getMin() {
                return Min;
            }

            public void setMin(String Min) {
                this.Min = Min;
            }

            public String getMax() {
                return Max;
            }

            public void setMax(String Max) {
                this.Max = Max;
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
        }
    }
}
