package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-10-17.
 * 说明:
 */
public class GetAboutUs extends RBResponse {

    /**
     * Introduce : 小溪汇聚专注于中央空调能效提升和运维管理，以互联网和大数据为平台，提供中央空调能源审计、故障诊断、系统调适、节能改造、运维托管、技能培训等全业态产品、服务和解决方案。
     * AppVersion : V1.0.0
     * LogoUrl : images/logo/LOGO-1-small.jpg
     * SystemInfoId : 0d9c0a913e13483bb2fc19c67fd1ab8e
     */

    private AboutUs resobj;

    public AboutUs getResobj() {
        return resobj;
    }

    public void setResobj(AboutUs resobj) {
        this.resobj = resobj;
    }

    public static class AboutUs {
        private String Introduce;
        //private String AppVersion;
        private String LogoUrl;
        private String SystemInfoId;

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String Introduce) {
            this.Introduce = Introduce;
        }

        //public String getAppVersion() {
        //    return AppVersion;
        //}

        // public void setAppVersion(String AppVersion) {
        //    this.AppVersion = AppVersion;
        //}

        public String getLogoUrl() {
            return LogoUrl;
        }

        public void setLogoUrl(String LogoUrl) {
            this.LogoUrl = LogoUrl;
        }

        public String getSystemInfoId() {
            return SystemInfoId;
        }

        public void setSystemInfoId(String SystemInfoId) {
            this.SystemInfoId = SystemInfoId;
        }
    }
}
