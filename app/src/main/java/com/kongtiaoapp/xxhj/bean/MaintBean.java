package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/5/30.
 */

public class MaintBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"DeviceType":"Boiler","MaintenanceRecordId":"78baa2c274f94cce86c23dcaeb6987oi","Title":"地板","UserName":"恒通国际创新园测试"},{"DeviceType":"Boiler","MaintenanceRecordId":"e586a82b4e0547c1ae372096b124e0a9","Title":"水管","UserName":"恒通国际创新园测试"}]
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
         * DeviceType : Boiler
         * MaintenanceRecordId : 78baa2c274f94cce86c23dcaeb6987oi
         * Title : 地板
         * UserName : 恒通国际创新园测试
         */

        private String DeviceType;
        private String MaintenanceRecordId;
        private String Title;
        private String UserName;
        private String MaintTime;
        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String DeviceType) {
            this.DeviceType = DeviceType;
        }

        public String getMaintenanceRecordId() {
            return MaintenanceRecordId;
        }

        public void setMaintenanceRecordId(String MaintenanceRecordId) {
            this.MaintenanceRecordId = MaintenanceRecordId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getMaintTime() {
            return MaintTime;
        }

        public void setMaintTime(String maintTime) {
            MaintTime = maintTime;
        }
    }
}
