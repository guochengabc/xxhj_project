package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/3/16.
 */
public class Project_Detail_FirstBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"ProjectId":"b37029c87f0fd9685b72eb2acd519cdb","RunStatus":"差","Comment":[{"UserName":"李润石","UserId":"66812464ef53415080ce6156bcb4cd30","Comments":[{"UserName":"小溪","UserId":"b4fd70c18307472690661dae63d18d5c","ToCommentId":"82657dcde8f34ae9b6f1d4cd459ca230","CreateTime":"1491961751","CommentInfo":"您的建议是我们做好软件最大的动力。","CommentId":"2e4a0bc842e147daafca50d0d271a7d6"}],"CreateTime":"1491961610","CommentInfo":"这个软件用的还挺舒服的，建议把项目信息的条目先隐藏起来。","CommentId":"82657dcde8f34ae9b6f1d4cd459ca230"}],"DiagCategory":[{"ChartNum":2,"Code":"OW--EC","Text":"电能消耗"},{"ChartNum":2,"Code":"COP--PR","Text":"制冷效率"},{"ChartNum":1,"Code":"HP","Text":"泵效率"},{"ChartNum":2,"Code":"CHWT--COWT","Text":"供回水"}],"DutyInfo":{"UserName":"小溪","Phone":"13701010102"},"EnergyInfo":{"CYdayEnergy":"+29kWh","OptEnergy":"560kWh","SaveEnergy":"155kWh","TotalEnergy":"715kWh","UnitEnergy":"1W/㎡","YdayEnergy":"687kWh"},"ProjectInfo":{"BuildingType":"行政办公建筑","City":"北京市","CoolingArea":"60692","EndDate":"1506787200","HeatingArea":"60692","ProjectName":"核心大楼(制冷)","ProjectType":"A","StartDate":"1493568000"}}
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
         * ProjectId : b37029c87f0fd9685b72eb2acd519cdb
         * RunStatus : 差
         * Comment : [{"UserName":"李润石","UserId":"66812464ef53415080ce6156bcb4cd30","Comments":[{"UserName":"小溪","UserId":"b4fd70c18307472690661dae63d18d5c","ToCommentId":"82657dcde8f34ae9b6f1d4cd459ca230","CreateTime":"1491961751","CommentInfo":"您的建议是我们做好软件最大的动力。","CommentId":"2e4a0bc842e147daafca50d0d271a7d6"}],"CreateTime":"1491961610","CommentInfo":"这个软件用的还挺舒服的，建议把项目信息的条目先隐藏起来。","CommentId":"82657dcde8f34ae9b6f1d4cd459ca230"}]
         * DiagCategory : [{"ChartNum":2,"Code":"OW--EC","Text":"电能消耗"},{"ChartNum":2,"Code":"COP--PR","Text":"制冷效率"},{"ChartNum":1,"Code":"HP","Text":"泵效率"},{"ChartNum":2,"Code":"CHWT--COWT","Text":"供回水"}]
         * DutyInfo : {"UserName":"小溪","Phone":"13701010102"}
         * EnergyInfo : {"CYdayEnergy":"+29kWh","OptEnergy":"560kWh","SaveEnergy":"155kWh","TotalEnergy":"715kWh","UnitEnergy":"1W/㎡","YdayEnergy":"687kWh"}
         * ProjectInfo : {"BuildingType":"行政办公建筑","City":"北京市","CoolingArea":"60692","EndDate":"1506787200","HeatingArea":"60692","ProjectName":"核心大楼(制冷)","ProjectType":"A","StartDate":"1493568000"}
         */

        private String ProjectId;
        private String RunStatus;
        private DutyInfoBean DutyInfo;
        private EnergyInfoBean EnergyInfo;
        private ProjectInfoBean ProjectInfo;
        private List<CommentBean> Comment;
        private List<DiagCategoryBean> DiagCategory;

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }

        public String getRunStatus() {
            return RunStatus;
        }

        public void setRunStatus(String RunStatus) {
            this.RunStatus = RunStatus;
        }

        public DutyInfoBean getDutyInfo() {
            return DutyInfo;
        }

        public void setDutyInfo(DutyInfoBean DutyInfo) {
            this.DutyInfo = DutyInfo;
        }

        public EnergyInfoBean getEnergyInfo() {
            return EnergyInfo;
        }

        public void setEnergyInfo(EnergyInfoBean EnergyInfo) {
            this.EnergyInfo = EnergyInfo;
        }

        public ProjectInfoBean getProjectInfo() {
            return ProjectInfo;
        }

        public void setProjectInfo(ProjectInfoBean ProjectInfo) {
            this.ProjectInfo = ProjectInfo;
        }

        public List<CommentBean> getComment() {
            return Comment;
        }

        public void setComment(List<CommentBean> Comment) {
            this.Comment = Comment;
        }

        public List<DiagCategoryBean> getDiagCategory() {
            return DiagCategory;
        }

        public void setDiagCategory(List<DiagCategoryBean> DiagCategory) {
            this.DiagCategory = DiagCategory;
        }

        public static class DutyInfoBean {
            /**
             * UserName : 小溪
             * Phone : 13701010102
             */

            private String UserName;
            private String Phone;

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }
        }

        public static class EnergyInfoBean {
            /**
             * CYdayEnergy : +29kWh
             * OptEnergy : 560kWh
             * SaveEnergy : 155kWh
             * TotalEnergy : 715kWh
             * UnitEnergy : 1W/㎡
             * YdayEnergy : 687kWh
             */

            private String CYdayEnergy;
            private String OptEnergy;
            private String SaveEnergy;
            private String TotalEnergy;
            private String UnitEnergy;
            private String YdayEnergy;

            public String getCYdayEnergy() {
                return CYdayEnergy;
            }

            public void setCYdayEnergy(String CYdayEnergy) {
                this.CYdayEnergy = CYdayEnergy;
            }

            public String getOptEnergy() {
                return OptEnergy;
            }

            public void setOptEnergy(String OptEnergy) {
                this.OptEnergy = OptEnergy;
            }

            public String getSaveEnergy() {
                return SaveEnergy;
            }

            public void setSaveEnergy(String SaveEnergy) {
                this.SaveEnergy = SaveEnergy;
            }

            public String getTotalEnergy() {
                return TotalEnergy;
            }

            public void setTotalEnergy(String TotalEnergy) {
                this.TotalEnergy = TotalEnergy;
            }

            public String getUnitEnergy() {
                return UnitEnergy;
            }

            public void setUnitEnergy(String UnitEnergy) {
                this.UnitEnergy = UnitEnergy;
            }

            public String getYdayEnergy() {
                return YdayEnergy;
            }

            public void setYdayEnergy(String YdayEnergy) {
                this.YdayEnergy = YdayEnergy;
            }
        }

        public static class ProjectInfoBean {
            /**
             * BuildingType : 行政办公建筑
             * City : 北京市
             * CoolingArea : 60692
             * EndDate : 1506787200
             * HeatingArea : 60692
             * ProjectName : 核心大楼(制冷)
             * ProjectType : A
             * StartDate : 1493568000
             */

            private String BuildingType;
            private String City;
            private String CoolingArea;
            private String EndDate;
            private String HeatingArea;
            private String ProjectName;
            private String ProjectType;
            private String StartDate;

            public String getBuildingType() {
                return BuildingType;
            }

            public void setBuildingType(String BuildingType) {
                this.BuildingType = BuildingType;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getCoolingArea() {
                return CoolingArea;
            }

            public void setCoolingArea(String CoolingArea) {
                this.CoolingArea = CoolingArea;
            }

            public String getEndDate() {
                return EndDate;
            }

            public void setEndDate(String EndDate) {
                this.EndDate = EndDate;
            }

            public String getHeatingArea() {
                return HeatingArea;
            }

            public void setHeatingArea(String HeatingArea) {
                this.HeatingArea = HeatingArea;
            }

            public String getProjectName() {
                return ProjectName;
            }

            public void setProjectName(String ProjectName) {
                this.ProjectName = ProjectName;
            }

            public String getProjectType() {
                return ProjectType;
            }

            public void setProjectType(String ProjectType) {
                this.ProjectType = ProjectType;
            }

            public String getStartDate() {
                return StartDate;
            }

            public void setStartDate(String StartDate) {
                this.StartDate = StartDate;
            }
        }

        public static class CommentBean {
            /**
             * UserName : 李润石
             * UserId : 66812464ef53415080ce6156bcb4cd30
             * Comments : [{"UserName":"小溪","UserId":"b4fd70c18307472690661dae63d18d5c","ToCommentId":"82657dcde8f34ae9b6f1d4cd459ca230","CreateTime":"1491961751","CommentInfo":"您的建议是我们做好软件最大的动力。","CommentId":"2e4a0bc842e147daafca50d0d271a7d6"}]
             * CreateTime : 1491961610
             * CommentInfo : 这个软件用的还挺舒服的，建议把项目信息的条目先隐藏起来。
             * CommentId : 82657dcde8f34ae9b6f1d4cd459ca230
             */

            private String UserName;
            private String UserId;
            private String CreateTime;
            private String CommentInfo;
            private String CommentId;
            private List<CommentsBean> Comments;

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getCommentInfo() {
                return CommentInfo;
            }

            public void setCommentInfo(String CommentInfo) {
                this.CommentInfo = CommentInfo;
            }

            public String getCommentId() {
                return CommentId;
            }

            public void setCommentId(String CommentId) {
                this.CommentId = CommentId;
            }

            public List<CommentsBean> getComments() {
                return Comments;
            }

            public void setComments(List<CommentsBean> Comments) {
                this.Comments = Comments;
            }

            public static class CommentsBean {
                /**
                 * UserName : 小溪
                 * UserId : b4fd70c18307472690661dae63d18d5c
                 * ToCommentId : 82657dcde8f34ae9b6f1d4cd459ca230
                 * CreateTime : 1491961751
                 * CommentInfo : 您的建议是我们做好软件最大的动力。
                 * CommentId : 2e4a0bc842e147daafca50d0d271a7d6
                 */

                private String UserName;
                private String UserId;
                private String ToCommentId;
                private String CreateTime;
                private String CommentInfo;
                private String CommentId;

                public String getUserName() {
                    return UserName;
                }

                public void setUserName(String UserName) {
                    this.UserName = UserName;
                }

                public String getUserId() {
                    return UserId;
                }

                public void setUserId(String UserId) {
                    this.UserId = UserId;
                }

                public String getToCommentId() {
                    return ToCommentId;
                }

                public void setToCommentId(String ToCommentId) {
                    this.ToCommentId = ToCommentId;
                }

                public String getCreateTime() {
                    return CreateTime;
                }

                public void setCreateTime(String CreateTime) {
                    this.CreateTime = CreateTime;
                }

                public String getCommentInfo() {
                    return CommentInfo;
                }

                public void setCommentInfo(String CommentInfo) {
                    this.CommentInfo = CommentInfo;
                }

                public String getCommentId() {
                    return CommentId;
                }

                public void setCommentId(String CommentId) {
                    this.CommentId = CommentId;
                }
            }
        }

        public static class DiagCategoryBean {
            /**
             * ChartNum : 2
             * Code : OW--EC
             * Text : 电能消耗
             */

            private int ChartNum;
            private String Code;
            private String Text;

            public int getChartNum() {
                return ChartNum;
            }

            public void setChartNum(int ChartNum) {
                this.ChartNum = ChartNum;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }
        }
    }
}
