package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luoye on 2016-9-12.
 * 说明:
 */
public class getProjectInfo extends RBResponse {

    /**
     * Apartment :
     * Gym : f
     * PipeLength : 30
     * TempDiff : 5
     * Mall :
     * Hospital :
     * Hotel :
     * UserId : fa642bf648234d7791db8a9236856ba2
     * Broadcast :
     * City : 香格里拉
     * Name : 项目名称
     * Time : 2016-08-02 11:00:58.083
     * Transport :
     * Museum :
     * OrgId : 8b1dafc0294748588a4ebc65f24bc9ba
     * PipeSystem : A
     * BuildingName : f09a90820a7645f9b67da21c18ad0094
     * Kindergarden :
     * Research :
     * School :
     * Office : 500
     * BuildingType : A
     * CoolingArea : 10000
     * ProjectId : 0610818c488e4f388c22db0434716e3e
     */

    private ProcjectInfo resobj;

    public ProcjectInfo getResobj() {
        return resobj;
    }

    public void setResobj(ProcjectInfo resobj) {
        this.resobj = resobj;
    }

    public static class ProcjectInfo {
        private String Apartment;//公寓
        private String Gym;//体育馆
        private String PipeLength;//最不利管路长度
        private String TempDiff;//供回水温差
        private String Mall;//商业
        private String Hospital;//医院
        private String Hotel;//宾馆
        private String UserId;
        private String Broadcast;//通信广播建筑
        private String City;//城市
        private String Name;//项目名称
        private String Time;
        private String Transport;//交通
        private String Museum;//观览
        private String OrgId;//项目公司ID
        private String PipeSystem;//管路系统
        private String BuildingName;//建筑物名称
        private String Kindergarden;//托教建筑
        private String Research;//科研
        private String School;//文教建筑
        private String Office;//行政办公
        private String BuildingType;//建筑物类型
        private String CoolingArea;//制冷区域总面积
        private String ProjectId;//项目编号
        private String UserName;//用户名
        private String HeatingArea;//供暖面积
        private String ProjectType;//A：制冷 B: 供暖
        private String StartDate;
        private String EndDate;

        public List<DevicesBean> getDevices() {
            return Devices;
        }

        public void setDevices(List<DevicesBean> devices) {
            Devices = devices;
        }

        private List<DevicesBean> Devices;

        public static class DevicesBean implements Serializable {
            private String DeviceName;
            private String DeviceId;
            private String DeviceType;

            public String getDeviceType() {
                return DeviceType;
            }

            public void setDeviceType(String deviceType) {
                DeviceType = deviceType;
            }

            public String getDeviceName() {
                return DeviceName;
            }

            public void setDeviceName(String deviceName) {
                DeviceName = deviceName;
            }

            public String getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(String deviceId) {
                DeviceId = deviceId;
            }
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getApartment() {
            return Apartment;
        }

        public void setApartment(String Apartment) {
            this.Apartment = Apartment;
        }

        public String getGym() {
            return Gym;
        }

        public void setGym(String Gym) {
            this.Gym = Gym;
        }

        public String getPipeLength() {
            return PipeLength;
        }

        public void setPipeLength(String PipeLength) {
            this.PipeLength = PipeLength;
        }

        public String getTempDiff() {
            return TempDiff;
        }

        public void setTempDiff(String TempDiff) {
            this.TempDiff = TempDiff;
        }

        public String getMall() {
            return Mall;
        }

        public void setMall(String Mall) {
            this.Mall = Mall;
        }

        public String getHospital() {
            return Hospital;
        }

        public void setHospital(String Hospital) {
            this.Hospital = Hospital;
        }

        public String getHotel() {
            return Hotel;
        }

        public void setHotel(String Hotel) {
            this.Hotel = Hotel;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getBroadcast() {
            return Broadcast;
        }

        public void setBroadcast(String Broadcast) {
            this.Broadcast = Broadcast;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getTransport() {
            return Transport;
        }

        public void setTransport(String Transport) {
            this.Transport = Transport;
        }

        public String getMuseum() {
            return Museum;
        }

        public void setMuseum(String Museum) {
            this.Museum = Museum;
        }

        public String getOrgId() {
            return OrgId;
        }

        public void setOrgId(String OrgId) {
            this.OrgId = OrgId;
        }

        public String getPipeSystem() {
            return PipeSystem;
        }

        public void setPipeSystem(String PipeSystem) {
            this.PipeSystem = PipeSystem;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String BuildingName) {
            this.BuildingName = BuildingName;
        }

        public String getKindergarden() {
            return Kindergarden;
        }

        public void setKindergarden(String Kindergarden) {
            this.Kindergarden = Kindergarden;
        }

        public String getResearch() {
            return Research;
        }

        public void setResearch(String Research) {
            this.Research = Research;
        }

        public String getSchool() {
            return School;
        }

        public void setSchool(String School) {
            this.School = School;
        }

        public String getOffice() {
            return Office;
        }

        public void setOffice(String Office) {
            this.Office = Office;
        }

        public String getBuildingType() {
            return BuildingType;
        }

        public void setBuildingType(String BuildingType) {
            this.BuildingType = BuildingType;
        }

        public String getCoolingArea() {
            return CoolingArea;
        }

        public void setCoolingArea(String CoolingArea) {
            this.CoolingArea = CoolingArea;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public String getHeatingArea() {
            return HeatingArea;
        }

        public void setHeatingArea(String heatingArea) {
            HeatingArea = heatingArea;
        }

        public String getProjectType() {
            return ProjectType;
        }

        public void setProjectType(String projectType) {
            ProjectType = projectType;
        }

        public String getStartDate() {
            return StartDate;
        }

        public void setStartDate(String startDate) {
            StartDate = startDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String endDate) {
            EndDate = endDate;
        }
    }
}
