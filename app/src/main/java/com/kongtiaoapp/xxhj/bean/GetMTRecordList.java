package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-5.
 * 说明:
 */
public class GetMTRecordList extends RBResponse {

    /**
     * DeviceName : 冷却塔
     * CreateTime : 2016-09-01 14:37:10.007
     * UserName : 草帽
     * MaintenanceRecordId : 0735884196424356875102b3968a98e1
     */

    private List<MTRecord> resobj;

    public List<MTRecord> getResobj() {
        return resobj;
    }

    public void setResobj(List<MTRecord> resobj) {
        this.resobj = resobj;
    }

    public static class MTRecord {
        private String DeviceName;
        private String CreateTime;
        private String UserName;
        private String MaintenanceRecordId;
        private String DeviceType;
        private String UserId;
        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String deviceType) {
            DeviceType = deviceType;
        }

        public String getDeviceName() {
            return DeviceName;
        }

        public void setDeviceName(String DeviceName) {
            this.DeviceName = DeviceName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getMaintenanceRecordId() {
            return MaintenanceRecordId;
        }

        public void setMaintenanceRecordId(String MaintenanceRecordId) {
            this.MaintenanceRecordId = MaintenanceRecordId;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }
    }
}
