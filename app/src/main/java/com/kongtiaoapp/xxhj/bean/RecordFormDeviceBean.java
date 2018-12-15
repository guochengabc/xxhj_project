package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/11/10.
 */

public class RecordFormDeviceBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"Name":"暖通空调","RecordFormMM":[{"Count":3,"Data":[{"DeviceId":"60e8ff4e1ddb476bb70e6e52cb500ac1","Name":"1#供热循环泵","Type":"rsxhbcs"},{"DeviceId":"33a6b423a7274faa8813cf8708df86a1","Name":"2#供热循环泵","Type":"rsxhbcs"},{"DeviceId":"f14c027e095a4796bc96d9818548eeab","Name":"3#供热循环泵","Type":"rsxhbcs"}],"DeviceType":"SourceHeatingPump","deviceName":"热水循环泵"}]}]
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
         * Name : 暖通空调
         * RecordFormMM : [{"Count":3,"Data":[{"DeviceId":"60e8ff4e1ddb476bb70e6e52cb500ac1","Name":"1#供热循环泵","Type":"rsxhbcs"},{"DeviceId":"33a6b423a7274faa8813cf8708df86a1","Name":"2#供热循环泵","Type":"rsxhbcs"},{"DeviceId":"f14c027e095a4796bc96d9818548eeab","Name":"3#供热循环泵","Type":"rsxhbcs"}],"DeviceType":"SourceHeatingPump","deviceName":"热水循环泵"}]
         */

        private String Name;
        private List<RecordFormBean> RecordForm;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public List<RecordFormBean> getRecordForm() {
            return RecordForm;
        }

        public void setRecordForm(List<RecordFormBean> RecordForm) {
            this.RecordForm = RecordForm;
        }

        public static class RecordFormBean {
            /**
             * Count : 3
             * Data : [{"DeviceId":"60e8ff4e1ddb476bb70e6e52cb500ac1","Name":"1#供热循环泵","Type":"rsxhbcs"},{"DeviceId":"33a6b423a7274faa8813cf8708df86a1","Name":"2#供热循环泵","Type":"rsxhbcs"},{"DeviceId":"f14c027e095a4796bc96d9818548eeab","Name":"3#供热循环泵","Type":"rsxhbcs"}]
             * DeviceType : SourceHeatingPump
             * deviceName : 热水循环泵
             */

            private int Count;
            private String DeviceType;
            private String deviceName;
            private List<DataBean> Data;

            public int getCount() {
                return Count;
            }

            public void setCount(int Count) {
                this.Count = Count;
            }

            public String getDeviceType() {
                return DeviceType;
            }

            public void setDeviceType(String DeviceType) {
                this.DeviceType = DeviceType;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public List<DataBean> getData() {
                return Data;
            }

            public void setData(List<DataBean> Data) {
                this.Data = Data;
            }

            public static class DataBean {
                /**
                 * DeviceId : 60e8ff4e1ddb476bb70e6e52cb500ac1
                 * Name : 1#供热循环泵
                 * Type : rsxhbcs
                 */

                private String DeviceId;
                private String Name;
                private String Type;

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

                public String getType() {
                    return Type;
                }

                public void setType(String Type) {
                    this.Type = Type;
                }
            }
        }
    }
}
