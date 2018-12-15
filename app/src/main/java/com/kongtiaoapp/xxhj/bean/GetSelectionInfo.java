package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-10.
 * 说明:
 */
public class GetSelectionInfo extends RBResponse {

    /**
     * Comments : [{"CommentInfo":"月礼服假面","CreateTime":"1473735604","ToUserId":"","UserName":"李四","UserId":"fa642bf648234d7791db8a9236856ba2","CommentId":"96aa486e8c9849b2abee91ea8e2324a4","AvatarUrl":"images/avatar/66812464ef53415080ce6156bcb4cd30.jpg","ToUserName":""}]
     * Date : 2016-09-13 11:00:04.577
     * IsCollection : 0
     * SelectedInfoId : e54cfecae5a947d7a03e237ec8d76b0e
     * UserName : 李四
     * SelectedInfo : 代表月亮消灭你
     * UserId : fa642bf648234d7791db8a9236856ba2
     * Title : 美少女战士
     * Abstract :
     */

    private SelectionInfo resobj;

    public SelectionInfo getResobj() {
        return resobj;
    }

    public void setResobj(SelectionInfo resobj) {
        this.resobj = resobj;
    }

    public static class SelectionInfo {
        private String Date;
        private String IsCollection;
        private String SelectedInfoId;
        private String UserName;
        private String SelectedInfo;
        private String UserId;
        private String Title;
        private String Abstract;
        private String CreateTime;
        private String Content;

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        /**
         * CommentInfo : 月礼服假面
         * CreateTime : 1473735604
         * ToUserId :
         * UserName : 李四
         * UserId : fa642bf648234d7791db8a9236856ba2
         * CommentId : 96aa486e8c9849b2abee91ea8e2324a4
         * AvatarUrl : images/avatar/66812464ef53415080ce6156bcb4cd30.jpg
         * ToUserName :
         */

        private List<CommentsBean> Comments;

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(String IsCollection) {
            this.IsCollection = IsCollection;
        }

        public String getSelectedInfoId() {
            return SelectedInfoId;
        }

        public void setSelectedInfoId(String SelectedInfoId) {
            this.SelectedInfoId = SelectedInfoId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getSelectedInfo() {
            return SelectedInfo;
        }

        public void setSelectedInfo(String SelectedInfo) {
            this.SelectedInfo = SelectedInfo;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getAbstract() {
            return Abstract;
        }

        public void setAbstract(String Abstract) {
            this.Abstract = Abstract;
        }

        public List<CommentsBean> getComments() {
            return Comments;
        }

        public void setComments(List<CommentsBean> Comments) {
            this.Comments = Comments;
        }

        public static class CommentsBean {
            private String CommentInfo;
            private String CreateTime;
            private String ToUserId;
            private String UserName;
            private String UserId;
            private String CommentId;
            private String AvatarUrl;
            private String ToUserName;

            public String getCommentInfo() {
                return CommentInfo;
            }

            public void setCommentInfo(String CommentInfo) {
                this.CommentInfo = CommentInfo;
            }

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

            public String getCommentId() {
                return CommentId;
            }

            public void setCommentId(String CommentId) {
                this.CommentId = CommentId;
            }

            public String getAvatarUrl() {
                return AvatarUrl;
            }

            public void setAvatarUrl(String AvatarUrl) {
                this.AvatarUrl = AvatarUrl;
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
