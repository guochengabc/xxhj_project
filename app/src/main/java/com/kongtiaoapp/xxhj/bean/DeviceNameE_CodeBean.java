package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;

/**
 * Created by G_XXHJ on 2018/4/27.   读取二维码参数
 */

public class DeviceNameE_CodeBean implements Serializable{

    /**
     * DeviceId : 002ac93d7f6b483d9563abac51a2ba84
     * Name : 1号冷冻水泵
     * Type : ldsbcs
     */

    private String DeviceId;
    private String SensorId;
    private String Name;
    private String Type;
    private String Flag;

    public String getSensorId() {
        return SensorId;
    }

    public void setSensorId(String sensorId) {
        SensorId = sensorId;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
}
