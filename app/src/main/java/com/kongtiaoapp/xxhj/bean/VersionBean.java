package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2016/12/5.
 */
public class VersionBean extends RBResponse {


    /**
     * code : 40000
     * resobj : {"Description":["1.增加用户注册；","2.增加每日周报；","3.增加供暖实时数据录入与诊断功能；","4.增加运行建议提醒功能；","5.修改若干已知Bug。"],"Version":"1.0.1","AppUrl":"apkoxi_v1.0.1.apk","CreateTime":"1482132416"}
     * token : 38d3e03b410142a185749ff4f6d79365
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
         * Description : ["1.增加用户注册；","2.增加每日周报；","3.增加供暖实时数据录入与诊断功能；","4.增加运行建议提醒功能；","5.修改若干已知Bug。"]
         * Version : 1.0.1
         * AppUrl : apkoxi_v1.0.1.apk
         * CreateTime : 1482132416
         */

        private String Version;
        private String AppUrl;
        private String CreateTime;
        private List<String> Description;

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public String getAppUrl() {
            return AppUrl;
        }

        public void setAppUrl(String AppUrl) {
            this.AppUrl = AppUrl;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public List<String> getDescription() {
            return Description;
        }

        public void setDescription(List<String> Description) {
            this.Description = Description;
        }
    }
}
