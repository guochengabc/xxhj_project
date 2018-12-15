package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-9-15.
 * 说明:
 */
public class WeibaoBean extends RBResponse {

    /**
     * Cost : 1234
     * DeviceId : b70cc777de8b4eb7ad0278de054f5f8f
     * DeviceName : 冷却塔
     * Phone : 12345678909
     * Time : 2016-08-25 12:52:30.0
     * Technician : 逗逗姐
     * UserName : 草帽
     * Company : 北大
     * Content : 维保内容
     * MaintenanceRecordId : 4dfe1e9322f640e0877de7e5b906c153
     */

    private WeiBao resobj;

    public WeiBao getResobj() {
        return resobj;
    }

    public void setResobj(WeiBao resobj) {
        this.resobj = resobj;
    }

    public static class WeiBao {
        private String Cost;//维保费用
        private String DeviceId;//设备为ID
        private String DeviceName;//设备名称
        private String Phone;//联系电话
        private String Time;//维保时间
        private String Technician;//维保人员
        private String UserName;//值班人
        private String Company;//维保公司
        private String Content;//维保内容
        private String MaintenanceRecordId;//维保记录ID
        private String DeviceType;//设备类型
        private String UserId;//值班人Id

        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String deviceType) {
            DeviceType = deviceType;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getCost() {
            return Cost;
        }

        public void setCost(String Cost) {
            this.Cost = Cost;
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

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getTechnician() {
            return Technician;
        }

        public void setTechnician(String Technician) {
            this.Technician = Technician;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getMaintenanceRecordId() {
            return MaintenanceRecordId;
        }

        public void setMaintenanceRecordId(String MaintenanceRecordId) {
            this.MaintenanceRecordId = MaintenanceRecordId;
        }
    }
}
