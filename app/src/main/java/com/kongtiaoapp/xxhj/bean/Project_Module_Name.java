package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/4/5.
 */
public class Project_Module_Name extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"TemplateName":"锅炉设备能源消耗表","TemplateId":"db7c248d9ea7b79418fca1605cab04a5"},{"TemplateName":"直燃机溴化锂机组运行点检表（冬季）","TemplateId":"28372837498ffbef29a2a9e2cdfef83e"}]
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
         * TemplateName : 锅炉设备能源消耗表
         * TemplateId : db7c248d9ea7b79418fca1605cab04a5
         */

        private String TemplateName;
        private String TemplateId;

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
