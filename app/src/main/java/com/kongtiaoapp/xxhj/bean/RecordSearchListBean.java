package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * 表单查询列表
 */
public class RecordSearchListBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"data":[{"recordTime":"2018-11-30 22:00:18","name":"餐厅燃气表","time":"2018-11-30 22:00:18","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"2018-11-29 18:40:04","name":"园区东南角(市政给水)","time":"2018-11-29 18:40:04","userName":"路彬","sensorId":"ee735e6bba1e4211ac94c6fdf295a800"},{"recordTime":"2018-11-29 18:18:55","name":"餐厅燃气表","time":"2018-11-29 18:18:55","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"2018-11-28 19:05:41","name":"餐厅燃气表","time":"2018-11-28 19:05:41","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"2018-11-27 18:10:26","name":"餐厅燃气表","time":"2018-11-27 18:10:26","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"2018-11-26 19:16:09","name":"餐厅燃气表","time":"2018-11-26 19:16:09","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"2018-11-26 18:12:28","name":"园区东南角(市政给水)","time":"2018-11-26 18:12:28","userName":"路彬","sensorId":"ee735e6bba1e4211ac94c6fdf295a800"},{"recordTime":"2018-11-26 18:12:28","name":"园区东南角(市政给水)","time":"2018-11-26 18:12:28","userName":"路彬","sensorId":"ee735e6bba1e4211ac94c6fdf295a800"},{"recordTime":"2018-11-25 19:21:02","name":"餐厅燃气表","time":"2018-11-25 19:21:02","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"2018-11-24 19:30:18","name":"餐厅燃气表","time":"2018-11-24 19:30:18","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"2018-11-23 17:52:01","name":"餐厅燃气表","time":"2018-11-23 17:52:01","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"Thu Nov 22 18:00:00 CST 2018","name":"餐厅燃气表","time":"2018-11-22 18:00:00","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"},{"recordTime":"Wed Nov 21 18:00:00 CST 2018","name":"餐厅燃气表","time":"2018-11-21 18:00:00","userName":"于善兴","sensorId":"EB7783E407024ACCA2771C622A32E6C8"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * recordTime : 2018-11-30 22:00:18
             * name : 餐厅燃气表
             * time : 2018-11-30 22:00:18
             * userName : 于善兴
             * sensorId : EB7783E407024ACCA2771C622A32E6C8
             */

            private String recordTime;
            private String name;
            private String time;
            private String userName;
            private String sensorId;

            public String getRecordTime() {
                return recordTime;
            }

            public void setRecordTime(String recordTime) {
                this.recordTime = recordTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getSensorId() {
                return sensorId;
            }

            public void setSensorId(String sensorId) {
                this.sensorId = sensorId;
            }
        }
    }
}
