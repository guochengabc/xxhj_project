package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-9-14.
 * 说明:
 */
public class RunningParam {
    private String Value;
    private String FieldType;
    private String Code;
    private String Unit;
    private String Data;
    private String Min;
    private String Max;
    private String Name;

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

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getMyContent() {
        return myContent;
    }

    public void setMyContent(String myContent) {
        this.myContent = myContent;
    }

    private String myContent;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
