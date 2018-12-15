package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/3/21.
 */

public class ProjectListBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"Phone":"13043952827","ProjectName":"轨道大厦","Pwd":"b9e79361b4040a3f3a71668163d2f058","Status":"0"},{"Phone":"13043952828","ProjectName":"望京(UCP)","Pwd":"b9e79361b4040a3f3a71668163d2f058","Status":"0"}]
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
         * Phone : 13043952827
         * ProjectName : 轨道大厦
         * Pwd : b9e79361b4040a3f3a71668163d2f058
         * Status : 0
         */

        private String Phone;
        private String ProjectName;
        private String Pwd;
        private String Status;

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getPwd() {
            return Pwd;
        }

        public void setPwd(String Pwd) {
            this.Pwd = Pwd;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
    }
}
