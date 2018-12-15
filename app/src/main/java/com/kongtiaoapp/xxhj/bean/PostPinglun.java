package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-26.
 * 说明:
 */
public class PostPinglun extends RBResponse {

    /**
     * Comments : [{"CreateTime":"1472543251","ToUserId":"d6408f41ec6b43889c4036622b8f995c","AnswerId":"b955e46701a54c4c9c196ecdedc157f4","UserName":"草帽","UserId":"fa642bf648234d7791db8a9236856ba2","Content":"评论内容","ToUserName":"大白"}]
     * CreateTime : 1472543184
     * AnswerId : b7981262d9444f259ced852472649a04
     * UserName : 草帽
     * UserId : fa642bf648234d7791db8a9236856ba2
     * CommentNumber : 2
     * PostId : 6ec8794d00a54ebaa15d7ce6c8186944
     * Content : 评论内容
     * AvatarUrl : image/avatar/fa642bf648234d7791db8a9236856ba2.jpg
     */

    private List<CommentsList> resobj;

    public List<CommentsList> getResobj() {
        return resobj;
    }

    public void setResobj(List<CommentsList> resobj) {
        this.resobj = resobj;
    }

    public static class CommentsList {
        private String CreateTime;
        private String AnswerId;
        private String UserName;
        private String UserId;
        private String CommentNumber;
        private String PostId;
        private String Content;
        private String AvatarUrl;
        /**
         * CreateTime : 1472543251
         * ToUserId : d6408f41ec6b43889c4036622b8f995c
         * AnswerId : b955e46701a54c4c9c196ecdedc157f4
         * UserName : 草帽
         * UserId : fa642bf648234d7791db8a9236856ba2
         * Content : 评论内容
         * ToUserName : 大白
         */

        private List<CommentsBean> Comments;

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getAnswerId() {
            return AnswerId;
        }

        public void setAnswerId(String AnswerId) {
            this.AnswerId = AnswerId;
        }

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

        public String getCommentNumber() {
            return CommentNumber;
        }

        public void setCommentNumber(String CommentNumber) {
            this.CommentNumber = CommentNumber;
        }

        public String getPostId() {
            return PostId;
        }

        public void setPostId(String PostId) {
            this.PostId = PostId;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String AvatarUrl) {
            this.AvatarUrl = AvatarUrl;
        }

        public List<CommentsBean> getComments() {
            return Comments;
        }

        public void setComments(List<CommentsBean> Comments) {
            this.Comments = Comments;
        }

        public static class CommentsBean {
            private String CreateTime;
            private String ToUserId;
            private String AnswerId;
            private String UserName;
            private String UserId;
            private String Content;
            private String ToUserName;

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getToUserId() {
                return ToUserId;
            }

            public void setToUserId(String ToUserId) {
                this.ToUserId = ToUserId;
            }

            public String getAnswerId() {
                return AnswerId;
            }

            public void setAnswerId(String AnswerId) {
                this.AnswerId = AnswerId;
            }

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

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }

            public String getToUserName() {
                return ToUserName;
            }

            public void setToUserName(String ToUserName) {
                this.ToUserName = ToUserName;
            }
        }
    }
}
