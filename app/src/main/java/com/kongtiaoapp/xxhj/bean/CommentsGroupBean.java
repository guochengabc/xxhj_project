package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/3/24.
 */
public class CommentsGroupBean extends RBResponse {

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * UserName : 赵六
         * UserId : 66812464ef53415080ce6156bcb4cd30
         * Comments : [{"UserName":"赵六","UserId":"66812464ef53415080ce6156bcb4cd30","ToCommentId":"50b9bcad2a1043e9a74974f4b6c8b184","CreateTime":"1489631922","CommentInfo":"评论内容123","CommentId":"a68b5a60f1464cd99bf982e279a1d301"},{"UserName":"赵六","UserId":"66812464ef53415080ce6156bcb4cd30","ToCommentId":"50b9bcad2a1043e9a74974f4b6c8b184","CreateTime":"1489631924","CommentInfo":"评论内容123","CommentId":"1f68a27f1aad4f1587fa075f1be4101d"}]
         * CreateTime : 1489631852
         * CommentInfo : 评论内容123
         * CommentId : 50b9bcad2a1043e9a74974f4b6c8b184
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
             * UserName : 赵六
             * UserId : 66812464ef53415080ce6156bcb4cd30
             * ToCommentId : 50b9bcad2a1043e9a74974f4b6c8b184
             * CreateTime : 1489631922
             * CommentInfo : 评论内容123
             * CommentId : a68b5a60f1464cd99bf982e279a1d301
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
}
