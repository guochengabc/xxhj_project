package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class ChartBeanDTO {


    /**
     * Data : {"tableName":"runningData","chartParam":[{"param":"ConsTotal","name":"风机能耗(kWh)","sParam":"","sName":"","divideNumber":5,"type":"normal","tableName":"Fan","DeviceId":"d99b3a61445742e"},{"param":"ConsTotal","name":"冷机能耗(kWh)","sParam":"","sName":"","divideNumber":8.33,"type":"normal","tableName":"Unit","DeviceId":"5d07cdade1f040e"}]}
     */

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * tableName : runningData
         * chartParam : [{"param":"ConsTotal","name":"风机能耗(kWh)","sParam":"","sName":"","divideNumber":5,"type":"normal","tableName":"Fan","DeviceId":"d99b3a61445742e"},{"param":"ConsTotal","name":"冷机能耗(kWh)","sParam":"","sName":"","divideNumber":8.33,"type":"normal","tableName":"Unit","DeviceId":"5d07cdade1f040e"}]
         */

        private String tableName;
        private List<ChartParamBean> chartParam;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public List<ChartParamBean> getChartParam() {
            return chartParam;
        }

        public void setChartParam(List<ChartParamBean> chartParam) {
            this.chartParam = chartParam;
        }

        public static class ChartParamBean {
            /**
             * param : ConsTotal
             * name : 风机能耗(kWh)
             * sParam :
             * sName :
             * divideNumber : 5
             * type : normal
             * tableName : Fan
             * DeviceId : d99b3a61445742e
             */

            private String param;
            private String name;
            private String sParam;
            private String sName;
            private double divideNumber;
            private String type;
            private String tableName;
            private String DeviceId;

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSParam() {
                return sParam;
            }

            public void setSParam(String sParam) {
                this.sParam = sParam;
            }

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public double getDivideNumber() {
                return divideNumber;
            }

            public void setDivideNumber(double divideNumber) {
                this.divideNumber = divideNumber;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTableName() {
                return tableName;
            }

            public void setTableName(String tableName) {
                this.tableName = tableName;
            }

            public String getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(String DeviceId) {
                this.DeviceId = DeviceId;
            }
        }
    }
}
