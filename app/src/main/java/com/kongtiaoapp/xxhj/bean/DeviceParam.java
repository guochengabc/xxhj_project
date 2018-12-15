package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-8-8.
 * 说明:
 */
public class DeviceParam extends RBResponse{

    /**
     * Value : 设备名称
     * Code : Name
     * Unit :
     */

    private List<DeviceParamList> resobj;

    public List<DeviceParamList> getResobj() {
        return resobj;
    }

    public void setResobj(List<DeviceParamList> resobj) {
        this.resobj = resobj;
    }

    public static class DeviceParamList {
        private String Value;
        private String Code;
        private String Unit;
        private String Data;
        private String FieldType;
        private List<EnumValue> EnumValue ;
        private String IsRequire;

        public String getIsRequire() {
            return IsRequire;
        }

        public void setIsRequire(String isRequire) {
            IsRequire = isRequire;
        }

        public List<DeviceParam.EnumValue> getEnumValue() {
            return EnumValue;
        }

        public void setEnumValue(List<DeviceParam.EnumValue> enumValue) {
            EnumValue = enumValue;
        }

        public String getFieldType() {
            return FieldType;
        }

        public void setFieldType(String fieldType) {
            FieldType = fieldType;
        }

        public String getData() {
            return Data;
        }

        public void setData(String data) {
            Data = data;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
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
    }

    public class EnumValue {
        private String Value;

        private String Code;

        public void setValue(String Value){
            this.Value = Value;
        }
        public String getValue(){
            return this.Value;
        }
        public void setCode(String Code){
            this.Code = Code;
        }
        public String getCode(){
            return this.Code;
        }

    }
}
