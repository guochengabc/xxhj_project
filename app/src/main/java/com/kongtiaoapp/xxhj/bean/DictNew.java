package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/9/29.
 */

public class DictNew extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"deviceName":"冷机","deviceType":"Unit"},{"deviceName":"一级冷冻水泵","deviceType":"FirstCoolingPump"},{"deviceName":"二级冷冻水泵","deviceType":"SecondCoolingPump"},{"deviceName":"冷却水泵","deviceType":"ChillingPump"},{"deviceName":"冷却塔","deviceType":"ChillingTower"}]
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
         * deviceName : 冷机
         * deviceType : Unit
         */

        private String deviceName;
        private String deviceType;

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }
    }
}
