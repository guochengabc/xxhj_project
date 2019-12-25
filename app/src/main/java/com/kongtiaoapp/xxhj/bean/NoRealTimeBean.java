package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class NoRealTimeBean extends RBResponse {


    /**
     * code : 40000
     * resobj : {"Statistical":"2019-12-18日\t\t喷淋\t\t1小时检测一次，检测的值为这小时内所走流量，这一天的累计注水量达到0.17m³","Data":[{"CoFDataInner":[0,0,0,0,0,0,0,0,0,0.17,0.17,0.17,0.17,0.17],"Counts":"14","DeviceId":"0b06cc3c9496452"}],"Flag":"S","Time":["01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00"],"MaxY":0.17,"MinY":0,"RunRecord":[{"BRunRecord":["消防栓2019-12-18 14:10:14开机","消防栓2019-12-18 02:45:00关机","消防栓2019-12-18 02:25:00开机","喷淋2019-12-18 02:55:00关机","喷淋2019-12-18 02:25:00开机"],"Timepl":"喷淋累计开机0.5小时","Timexxf":"消防栓累计开机0.33小时"}]}
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
         * Statistical : 2019-12-18日		喷淋		1小时检测一次，检测的值为这小时内所走流量，这一天的累计注水量达到0.17m³
         * Data : [{"CoFDataInner":[0,0,0,0,0,0,0,0,0,0.17,0.17,0.17,0.17,0.17],"Counts":"14","DeviceId":"0b06cc3c9496452"}]
         * Flag : S
         * Time : ["01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00"]
         * MaxY : 0.17
         * MinY : 0
         * RunRecord : [{"BRunRecord":["消防栓2019-12-18 14:10:14开机","消防栓2019-12-18 02:45:00关机","消防栓2019-12-18 02:25:00开机","喷淋2019-12-18 02:55:00关机","喷淋2019-12-18 02:25:00开机"],"Timepl":"喷淋累计开机0.5小时","Timexxf":"消防栓累计开机0.33小时"}]
         */

        private String Statistical;
        private String Flag;
        private double MaxY;
        private double MinY;
        private List<DataBean> Data;
        private List<String> Time;
        private List<RunRecordBean> RunRecord;

        public String getStatistical() {
            return Statistical;
        }

        public void setStatistical(String Statistical) {
            this.Statistical = Statistical;
        }

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String Flag) {
            this.Flag = Flag;
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

        public List<RunRecordBean> getRunRecord() {
            return RunRecord;
        }

        public void setRunRecord(List<RunRecordBean> RunRecord) {
            this.RunRecord = RunRecord;
        }

        public static class DataBean {
            /**
             * CoFDataInner : [0,0,0,0,0,0,0,0,0,0.17,0.17,0.17,0.17,0.17]
             * Counts : 14
             * DeviceId : 0b06cc3c9496452
             */
            private String DeviceName;
            private String Counts;
            private String DeviceId;
            private double[] CoFDataInner;

            public String getDeviceName() {
                return DeviceName;
            }

            public void setDeviceName(String tDeviceName) {
                this.DeviceName = tDeviceName;
            }

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

            public double[] getCoFDataInner() {
                return CoFDataInner;
            }

            public void setCoFDataInner(double[] CoFDataInner) {
                this.CoFDataInner = CoFDataInner;
            }
        }

        public static class RunRecordBean {
            /**
             * BRunRecord : ["消防栓2019-12-18 14:10:14开机","消防栓2019-12-18 02:45:00关机","消防栓2019-12-18 02:25:00开机","喷淋2019-12-18 02:55:00关机","喷淋2019-12-18 02:25:00开机"]
             * Timepl : 喷淋累计开机0.5小时
             * Timexxf : 消防栓累计开机0.33小时
             */

            private String Timepl;
            private String Timexxf;
            private List<String> BRunRecord;

            public String getTimepl() {
                return Timepl;
            }

            public void setTimepl(String Timepl) {
                this.Timepl = Timepl;
            }

            public String getTimexxf() {
                return Timexxf;
            }

            public void setTimexxf(String Timexxf) {
                this.Timexxf = Timexxf;
            }

            public List<String> getBRunRecord() {
                return BRunRecord;
            }

            public void setBRunRecord(List<String> BRunRecord) {
                this.BRunRecord = BRunRecord;
            }
        }
    }
}
