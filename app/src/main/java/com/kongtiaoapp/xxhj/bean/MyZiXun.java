package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-19.
 * 说明:我收藏的咨询
 */
public class MyZiXun extends RBResponse {

    /**
     * MsgId : e54cfecae5a947d7a03e237ec8d76b0e
     * Type : C
     * CreateTime : 1473735604
     * UserName : 李四
     * ImageUrl : ["images/selection/2016/09/14/493016874966003992.jpg"]
     * UserId : b4fd70c18307472690661dae63d18d5c
     * AvatarUrl : images/avatar/66812464ef53415080ce6156bcb4cd30.jpg
     * Title : 美少女战士
     * CollectionId : 2d379d1c52a8401abd9a9abb7a2e25bf
     */

    private List<MyZiXunBean> resobj;

    public List<MyZiXunBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<MyZiXunBean> resobj) {
        this.resobj = resobj;
    }

    public static class MyZiXunBean {
        private String MsgId;
        private String Type;
        private String CreateTime;
        private String UserName;
        private String UserId;
        private String AvatarUrl;
        private String Title;
        private String CollectionId;
        private String IsCollection;
        private List<String> ImageUrl;

        public String getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(String isCollection) {
            IsCollection = isCollection;
        }

        public String getMsgId() {
            return MsgId;
        }

        public void setMsgId(String MsgId) {
            this.MsgId = MsgId;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
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

        public String getCollectionId() {
            return CollectionId;
        }

        public void setCollectionId(String CollectionId) {
            this.CollectionId = CollectionId;
        }

        public List<String> getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(List<String> ImageUrl) {
            this.ImageUrl = ImageUrl;
        }
    }
}
