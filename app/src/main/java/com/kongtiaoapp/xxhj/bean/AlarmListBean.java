package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/9/25.
 */

public class AlarmListBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"alarmId":"f7215382f33b44bc818dd96eb995772b","alarmType":"严重告警","deviceName":"冷机(2)","keyWords":"严重告警：冷机(2) 出现故障, 故障为：冷机负载率过大","time":"2017-08-29 09:42:17"},{"alarmId":"fb80f6b17aa747deaf9c0c28454d75a6","alarmType":"严重告警","deviceName":"冷机(2)","keyWords":"严重告警：冷机(2) 出现故障, 故障为：冷机负载率过小","time":"2017-08-29 07:44:16"}]
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
         * alarmId : f7215382f33b44bc818dd96eb995772b
         * alarmType : 严重告警
         * deviceName : 冷机(2)
         * keyWords : 严重告警：冷机(2) 出现故障, 故障为：冷机负载率过大
         * time : 2017-08-29 09:42:17
         */

        private String alarmId;
        private String alarmType;
        private String deviceName;
        private String keyWords;
        private String time;

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
