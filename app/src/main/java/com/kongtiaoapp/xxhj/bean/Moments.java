package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luoye on 2016-8-1.
 * 说明:
 */
public class Moments extends RBResponse {

    /**
     * AvatarUrl : image/avatar/fa642bf648234d7791db8a9236856ba2.png
     * CommentNumber :
     * CreateTime : 2016-08-01 09:16:43.38
     * ImageUrl : ["image/message/b400801c31484b3b86c12bf00fba37f3.jpg"]
     * MessageId : 06b50fc5e82e49d98db33b745860ebaf
     * MessageInfo : ???é?|é￠ˉ???è?￥?2??????§è|?
     * UserId : fa642bf648234d7791db8a9236856ba2
     * UserName : 李四
     * WelldoneNumber :
     */

    private List<MomentInfo> resobj;

    public List<MomentInfo> getResobj() {
        return resobj;
    }

    public void setResobj(List<MomentInfo> resobj) {
        this.resobj = resobj;
    }

    public static class MomentInfo implements Serializable {
        private String AvatarUrl;//头像
        private String CommentNumber;//评论数
        private String CreateTime;//创建时间
        private String MessageId;//朋友圈ID
        private String MessageInfo;//内容详情
        private String UserId;//用户ID
        private String UserName;//用户名
        private String WelldoneNumber;//好评的计数
        private String IsWelldone;// 0 未点赞 1 已点赞
        private String IsCollection;// 0 未收藏 1 已收藏
        private List<String> ImageUrl;

        public String getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(String isCollection) {
            IsCollection = isCollection;
        }

        public String getIsWelldone() {
            return IsWelldone;
        }

        public void setIsWelldone(String isWelldone) {
            IsWelldone = isWelldone;
        }

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String AvatarUrl) {
            this.AvatarUrl = AvatarUrl;
        }

        public String getCommentNumber() {
            return CommentNumber;
        }

        public void setCommentNumber(String CommentNumber) {
            this.CommentNumber = CommentNumber;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getMessageId() {
            return MessageId;
        }

        public void setMessageId(String MessageId) {
            this.MessageId = MessageId;
        }

        public String getMessageInfo() {
            return MessageInfo;
        }

        public void setMessageInfo(String MessageInfo) {
            this.MessageInfo = MessageInfo;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getWelldoneNumber() {
            return WelldoneNumber;
        }

        public void setWelldoneNumber(String WelldoneNumber) {
            this.WelldoneNumber = WelldoneNumber;
        }

        public List<String> getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(List<String> ImageUrl) {
            this.ImageUrl = ImageUrl;
        }
    }
}
