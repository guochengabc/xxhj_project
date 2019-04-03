package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class HD_FreDTO {

    /**
     * Data : {"freParam":[{"param":"Exhaust_Freq_1","name":"东北风机频率","sParam":"RunnerFan_Freq_1","sName":"东北转轮频率","divideNumber":"10","type":"normal"},{"param":"Exhaust_Freq_2","name":"东南风机频率","sParam":"RunnerFan_Freq_2","sName":"东南转轮频率","divideNumber":"10","type":"normal"},{"param":"Exhaust_Freq_3","name":"西北风机频率","sParam":"RunnerFan_Freq_3","sName":"西北转轮频率","divideNumber":"10","type":"normal"},{"param":"Exhaust_Freq_4","name":"西南风机频率","sParam":"RunnerFan_Freq_4","sName":"西南转轮频率","divideNumber":"10","type":"normal"},{"param":"Exhaust_Freq","name":"风机频率","sParam":"RunnerFan_Freq","sName":"转轮频率","divideNumber":"10","type":"unNormal"}]}
     */

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private List<FreParamBean> freParam;

        public List<FreParamBean> getFreParam() {
            return freParam;
        }

        public void setFreParam(List<FreParamBean> freParam) {
            this.freParam = freParam;
        }

        public static class FreParamBean {
            /**
             * param : Exhaust_Freq_1
             * name : 东北风机频率
             * sParam : RunnerFan_Freq_1
             * sName : 东北转轮频率
             * divideNumber : 10
             * type : normal
             */

            private String param;
            private String name;
            private String sParam;
            private String sName;
            private String divideNumber;
            private String type;

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

            public String getDivideNumber() {
                return divideNumber;
            }

            public void setDivideNumber(String divideNumber) {
                this.divideNumber = divideNumber;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
