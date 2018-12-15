package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/9/25.
 */

public class AlarmDetailBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"alarmId":"f7215382f33b44bc818dd96eb995772b","alarmType":"A","deviceId":"c3549cdb445c4d34a2196749e9121562","deviceName":"冷机(2)","keyWords":"严重告警：冷机(2) 出现故障, 故障为：冷机负载率过大","onDutyId":"                                ","phone":"","projectId":"f25398f352d581fe6b95d0f59fd5d0ac","projectName":"轨道大厦(制冷)","projectType":"A","time":"2017-08-29 09:42:17","userName":""}
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
         * alarmId : f7215382f33b44bc818dd96eb995772b
         * alarmType : A
         * deviceId : c3549cdb445c4d34a2196749e9121562
         * deviceName : 冷机(2)
         * keyWords : 严重告警：冷机(2) 出现故障, 故障为：冷机负载率过大
         * onDutyId :
         * phone :
         * projectId : f25398f352d581fe6b95d0f59fd5d0ac
         * projectName : 轨道大厦(制冷)
         * projectType : A
         * time : 2017-08-29 09:42:17
         * userName :
         */

        private String alarmId;
        private String alarmType;
        private String deviceId;
        private String deviceName;
        private String keyWords;
        private String onDutyId;
        private String phone;
        private String projectId;
        private String projectName;
        private String projectType;
        private String time;
        private String userName;
        private String alarmTypeName;
        public String getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(String alarmId) {
            this.alarmId = alarmId;
        }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getKeyWords() {
            return keyWords;
        }

        public void setKeyWords(String keyWords) {
            this.keyWords = keyWords;
        }

        public String getOnDutyId() {
            return onDutyId;
        }

        public void setOnDutyId(String onDutyId) {
            this.onDutyId = onDutyId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAlarmTypeName() {
            return alarmTypeName;
        }

        public void setAlarmTypeName(String alarmTypeName) {
            this.alarmTypeName = alarmTypeName;
        }
    }
}
