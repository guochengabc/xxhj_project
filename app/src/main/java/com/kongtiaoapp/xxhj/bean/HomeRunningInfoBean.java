package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/10/19.
 */

public class HomeRunningInfoBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"modules":[{"moduleName":"HA","projectId":"f25398f352d581fe6b95d0f59fd5d0ac"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<ModulesBean> modules;
        private boolean isCommon;

        public boolean isCommon() {
            return isCommon;
        }

        public void setCommon(boolean common) {
            isCommon = common;
        }

        public List<ModulesBean> getModules() {
            return modules;
        }

        public void setModules(List<ModulesBean> modules) {
            this.modules = modules;
        }

        public static class ModulesBean {
            /**
             * moduleName : HA
             * projectId : f25398f352d581fe6b95d0f59fd5d0ac
             */

            private String moduleName;
            private String projectId;

            public String getModuleName() {
                return moduleName;
            }

            public void setModuleName(String moduleName) {
                this.moduleName = moduleName;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }
        }
    }
}
