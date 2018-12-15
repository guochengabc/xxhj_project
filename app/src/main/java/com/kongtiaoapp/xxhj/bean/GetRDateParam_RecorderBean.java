package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/4/15.
 */
public class GetRDateParam_RecorderBean extends RBResponse{

    /**
     * resobj : {"ProjectName":"望京(供暖)","ProjectId":"670a5988899b47cd94e792e33f82cfca","TemplateName":"锅炉设备能源消耗表","TemplateId":"96cea43b53b9f1f7c6e1d2eab8b1ac40"}
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
         * ProjectName : 望京(供暖)
         * ProjectId : 670a5988899b47cd94e792e33f82cfca
         * TemplateName : 锅炉设备能源消耗表
         * TemplateId : 96cea43b53b9f1f7c6e1d2eab8b1ac40
         */

        private String ProjectName;
        private String ProjectId;
        private String TemplateName;
        private String TemplateId;

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public String getTemplateName() {
            return TemplateName;
        }

        public void setTemplateName(String TemplateName) {
            this.TemplateName = TemplateName;
        }

        public String getTemplateId() {
            return TemplateId;
        }

        public void setTemplateId(String TemplateId) {
            this.TemplateId = TemplateId;
        }
    }
}
