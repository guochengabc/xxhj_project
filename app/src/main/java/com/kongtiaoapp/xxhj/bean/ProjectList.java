package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-5.
 * 说明:项目信息列表
 */
public class ProjectList extends RBResponse{


    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * Name : 供暖项目
         * ProjectType : B
         * CollectMode : A
         * Time : 1479975358
         * UserId : 66812464ef53415080ce6156bcb4cd30
         * ProjectId : 1b951ad8763d421089a76a462584b15a
         * BuildingName : 他
         */

        private String Name;
        private String ProjectType;
        private String CollectMode;
        private String Time;
        private String UserId;
        private String ProjectId;
        private String BuildingName;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getProjectType() {
            return ProjectType;
        }

        public void setProjectType(String ProjectType) {
            this.ProjectType = ProjectType;
        }

        public String getCollectMode() {
            return CollectMode;
        }

        public void setCollectMode(String CollectMode) {
            this.CollectMode = CollectMode;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String BuildingName) {
            this.BuildingName = BuildingName;
        }
    }
}
