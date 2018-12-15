package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-8-2.
 * 说明:
 */
public class Pinglun extends RBResponse {

    /**
     * UserId : 用户ID
     * UserName : 用户名
     * ToUserId : 被回复用户id
     * ToUserName : 被回复用户名
     * MessageInfo : 评论内容
     * MessageId : 编号
     * CreateTime : 创建时间
     */

    private List<PinglunInfo> resobj;

    public List<PinglunInfo> getResobj() {
        return resobj;
    }

    public void setResobj(List<PinglunInfo> resobj) {
        this.resobj = resobj;
    }

    public static class PinglunInfo {
        private String AvatarUrl;//头像
        private String CommentId;//评论ID
        private String CommentInfo;//评论内容
        private String CreateTime;//创建时间
        private String MessageId;//朋友圈ID
        private String ToUserId;//被评论人ID
        private String ToUserName;//被评论人名字
        private String UserId;//评论人ID
        private String UserName;//评论人名字

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            AvatarUrl = avatarUrl;
        }

        public String getCommentId() {
            return CommentId;
        }

        public void setCommentId(String commentId) {
            CommentId = commentId;
        }

        public String getCommentInfo() {
            return CommentInfo;
        }

        public void setCommentInfo(String commentInfo) {
            CommentInfo = commentInfo;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getMessageId() {
            return MessageId;
        }

        public void setMessageId(String messageId) {
            MessageId = messageId;
        }

        public String getToUserId() {
            return ToUserId;
        }

        public void setToUserId(String toUserId) {
            ToUserId = toUserId;
        }

        public String getToUserName() {
            return ToUserName;
        }

        public void setToUserName(String toUserName) {
            ToUserName = toUserName;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }
    }

}
