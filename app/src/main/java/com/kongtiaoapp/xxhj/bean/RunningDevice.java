package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-12.
 * 说明:
 */
public class RunningDevice extends RBResponse {

    /**
     * Name : 冷冻水泵
     * DeviceId : c3db048dc3934d9b8fdef52448e6bb2f
     * Type : first_cooling_pump
     * TypeName : 冷冻水泵
     */

    private List<RunningDeviceList> resobj;


    public List<RunningDeviceList> getResobj() {
        return resobj;
    }

    public void setResobj(List<RunningDeviceList> resobj) {
        this.resobj = resobj;
    }

    public static class RunningDeviceList {
        private String Name;
        private String DeviceId;
        private String Type;
        private String TypeName;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDeviceId() {
            return DeviceId;
        }

        public void setDeviceId(String DeviceId) {
            this.DeviceId = DeviceId;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }
    }
}
