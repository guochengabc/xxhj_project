package com.kongtiaoapp.xxhj.bean;

/**
 * Created by G_XXHJ on 2018/5/30.  维保详情
 */

public class WeiBaoDetailBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Company":"1212","Content":"1212","Cost":"1212","DeviceId":"f798013726bd4c27ada31693a719befd","DeviceName":"锅炉2#","DeviceType":"Boiler","MaintTime":"2018-5-30","MaintenanceRecordId":"78baa2c274f94cce86c23dcaeb6987oi","Phone":"121212","Remark":"北风呼呼的挂","Technician":"1212","Time":"1527526031","Title":"地板","UserId":"0ac3f69364154f19ac015af80221b311","UserName":"恒通国际创新园测试"}
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
         * Company : 1212
         * Content : 1212
         * Cost : 1212
         * DeviceId : f798013726bd4c27ada31693a719befd
         * DeviceName : 锅炉2#
         * DeviceType : Boiler
         * MaintTime : 2018-5-30
         * MaintenanceRecordId : 78baa2c274f94cce86c23dcaeb6987oi
         * Phone : 121212
         * Remark : 北风呼呼的挂
         * Technician : 1212
         * Time : 1527526031
         * Title : 地板
         * UserId : 0ac3f69364154f19ac015af80221b311
         * UserName : 恒通国际创新园测试
         */

        private String Company;
        private String Content;
        private String Cost;
        private String DeviceId;
        private String DeviceName;
        private String DeviceType;
        private String MaintTime;
        private String MaintenanceRecordId;
        private String Phone;
        private String Remark;
        private String Technician;
        private String Time;
        private String Title;
        private String UserId;
        private String UserName;

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

        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String DeviceType) {
            this.DeviceType = DeviceType;
        }

        public String getMaintTime() {
            return MaintTime;
        }

        public void setMaintTime(String MaintTime) {
            this.MaintTime = MaintTime;
        }

        public String getMaintenanceRecordId() {
            return MaintenanceRecordId;
        }

        public void setMaintenanceRecordId(String MaintenanceRecordId) {
            this.MaintenanceRecordId = MaintenanceRecordId;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getTechnician() {
            return Technician;
        }

        public void setTechnician(String Technician) {
            this.Technician = Technician;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }
    }
}
