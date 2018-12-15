package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-14.
 * 说明:
 */
public class GetFriendList extends RBResponse {

    /**
     * LastTime : 2016-09-07 10:08:22.61
     * UserName : B级公司总管理
     * UserId : d6408f41ec6b43889c4036622b8f995c
     * AvatarUrl : image/avatar/d6408f41ec6b43889c4036622b8f995c.jpg
     */

    private List<FriendList> resobj;

    public List<FriendList> getResobj() {
        return resobj;
    }

    public void setResobj(List<FriendList> resobj) {
        this.resobj = resobj;
    }

    public static class FriendList {
        private String LastTime;
        private String UserName;
        private String UserId;
        private String AvatarUrl;

        public String getLastTime() {
            return LastTime;
        }

        public void setLastTime(String LastTime) {
            this.LastTime = LastTime;
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
    }
}
