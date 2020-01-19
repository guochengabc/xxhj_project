package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class SystemBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"sysTem":[{"name":"暖通供暖","orgId":"955537C4AA7D40C6BA7CC683FE22F6B7","projectId":"460CBECDB63A4985A3AEE544EB4414F0","projectType":"B"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<SysTemBean> sysTem;

        public List<SysTemBean> getSysTem() {
            return sysTem;
        }

        public void setSysTem(List<SysTemBean> sysTem) {
            this.sysTem = sysTem;
        }

        public static class SysTemBean {
            /**
             * name : 暖通供暖
             * orgId : 955537C4AA7D40C6BA7CC683FE22F6B7
             * projectId : 460CBECDB63A4985A3AEE544EB4414F0
             * projectType : B
             */

            private String name;
            private String orgId;
            private String projectId;
            private String projectType;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOrgId() {
                return orgId;
            }

            public void setOrgId(String orgId) {
                this.orgId = orgId;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }
        }
    }
}
