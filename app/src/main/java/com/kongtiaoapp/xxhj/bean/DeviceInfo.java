package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luoye on 2016-8-8.
 * 说明:
 */

public class DeviceInfo extends RBResponse implements Serializable {

    /**
     * DeviceId : 设备ID
     * Name : 设备名称
     */

    private List<DeviceInfoList> resobj;

    public List<DeviceInfoList> getResobj() {
        return resobj;
    }

    public void setResobj(List<DeviceInfoList> resobj) {
        this.resobj = resobj;
    }

    public class DeviceInfoList implements Serializable {
        private String DeviceId;
        private String Name;
        private String DeviceType;
        private String IsUse = "0";
        private String UserId;
        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String deviceType) {
            DeviceType = deviceType;
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

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String isUse) {
            IsUse = isUse;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }
    }
}
