package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luoye on 2016-9-13.
 * 说明:讨论帖
 */
public class GetPostList extends RBResponse implements Serializable{

    /**
     * ThemeId : 6ec8794d00a54ebaa15d7ce6c8186944
     * UserName : 草帽
     * ImageUrl : ["image/message/13976e471099427c83994101cecfed12.jpg","image/message/4c7e1e51bdeb4741b8ce70e56ac63565.jpg"]
     * UserId : fa642bf648234d7791db8a9236856ba2
     * AvatarUrl : image/avatar/fa642bf648234d7791db8a9236856ba2.jpg
     * Title : 帖子标题
     * EliteStatus :
     * WelldoneNumber :
     * TopStatus :
     * IsWelldone : 0
     * CreateTime : 1472467093
     * CommentNumber : 4
     * Content : 这是一条条轮帖
     */

    private List<Post> resobj;

    public List<Post> getResobj() {
        return resobj;
    }

    public void setResobj(List<Post> resobj) {
        this.resobj = resobj;
    }

    public class Post implements Serializable {
        private String ThemeId;//帖子ID
        private String UserName;//发帖用户名
        private String UserId;//发帖用户Id
        private String AvatarUrl;//发帖用户头像url
        private String Title;//标题
        private String EliteStatus;//精华标识
        private String WelldoneNumber;//好评的计数
        private String TopStatus;//置顶标识
        private String IsWelldone;//是否好评
        private String IsCollection;//是否收藏
        private String CreateTime;//发帖时间
        private String CommentNumber;//评论数
        private String Content;//内容
        private List<String> ImageUrl;//帖子图片url数组

        public String getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(String isCollection) {
            IsCollection = isCollection;
        }

        public String getThemeId() {
            return ThemeId;
        }

        public void setThemeId(String ThemeId) {
            this.ThemeId = ThemeId;
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

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String AvatarUrl) {
            this.AvatarUrl = AvatarUrl;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getEliteStatus() {
            return EliteStatus;
        }

        public void setEliteStatus(String EliteStatus) {
            this.EliteStatus = EliteStatus;
        }

        public String getWelldoneNumber() {
            return WelldoneNumber;
        }

        public void setWelldoneNumber(String WelldoneNumber) {
            this.WelldoneNumber = WelldoneNumber;
        }

        public String getTopStatus() {
            return TopStatus;
        }

        public void setTopStatus(String TopStatus) {
            this.TopStatus = TopStatus;
        }

        public String getIsWelldone() {
            return IsWelldone;
        }

        public void setIsWelldone(String IsWelldone) {
            this.IsWelldone = IsWelldone;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCommentNumber() {
            return CommentNumber;
        }

        public void setCommentNumber(String CommentNumber) {
            this.CommentNumber = CommentNumber;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public List<String> getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(List<String> ImageUrl) {
            this.ImageUrl = ImageUrl;
        }
    }
}
