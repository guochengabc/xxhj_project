package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class NoRealTimeBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"MaxY":18,"MinY":0,"Data":[{"CoFDataInner":[0.16,0,18],"Counts":"3","DeviceId":"0b06cc3c9496452","DeviceName":"喷淋"}],"Flag":"S","Time":["2019-07-22 14:00:00","2019-07-22 14:10:00","2019-07-22 14:12:07"]}
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
         * MaxY : 18
         * MinY : 0
         * Data : [{"CoFDataInner":[0.16,0,18],"Counts":"3","DeviceId":"0b06cc3c9496452","DeviceName":"喷淋"}]
         * Flag : S
         * Time : ["2019-07-22 14:00:00","2019-07-22 14:10:00","2019-07-22 14:12:07"]
         */

        private double MaxY;
        private double MinY;
        private String Flag;
        private String Statistical;
        private List<DataBean> Data;
        private List<String> Time;

        public String getStatistical() {
            return Statistical;
        }

        public void setStatistical(String statistical) {
            Statistical = statistical;
        }

        public double getMaxY() {
            return MaxY;
        }

        public void setMaxY(double MaxY) {
            this.MaxY = MaxY;
        }

        public double getMinY() {
            return MinY;
        }

        public void setMinY(double MinY) {
            this.MinY = MinY;
        }

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String Flag) {
            this.Flag = Flag;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public List<String> getTime() {
            return Time;
        }

        public void setTime(List<String> Time) {
            this.Time = Time;
        }

        public static class DataBean {
            /**
             * CoFDataInner : [0.16,0,18]
             * Counts : 3
             * DeviceId : 0b06cc3c9496452
             * DeviceName : 喷淋
             */

            private String Counts;
            private String DeviceId;
            private String DeviceName;
            private double[] CoFDataInner;

            public String getCounts() {
                return Counts;
            }

            public void setCounts(String Counts) {
                this.Counts = Counts;
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

            public double[] getCoFDataInner() {
                return CoFDataInner;
            }

            public void setCoFDataInner(double[] CoFDataInner) {
                this.CoFDataInner = CoFDataInner;
            }
        }
    }
}
