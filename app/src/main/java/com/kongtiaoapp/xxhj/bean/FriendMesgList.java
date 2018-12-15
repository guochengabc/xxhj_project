package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-9.
 * 说明:
 */
public class FriendMesgList extends RBResponse {

    /**
     * Message : 测试不行删申请加好友
     * Type : friend
     * CreateTime : 1473316088
     * UserName : B级公司总管理
     * UserId : d6408f41ec6b43889c4036622b8f995c
     * SystemMessageId : 395375e351ff46f48dac79706f604eb7
     * AvatarUrl : image/avatar/d6408f41ec6b43889c4036622b8f995c.jpg
     */

    private List<FriendMesg> resobj;

    public List<FriendMesg> getResobj() {
        return resobj;
    }

    public void setResobj(List<FriendMesg> resobj) {
        this.resobj = resobj;
    }

    public static class FriendMesg {
        private String Message;
        private String Type;
        private String CreateTime;
        private String UserName;
        private String UserId;
        private String SystemMessageId;
        private String AvatarUrl;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
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

        public String getSystemMessageId() {
            return SystemMessageId;
        }

        public void setSystemMessageId(String SystemMessageId) {
            this.SystemMessageId = SystemMessageId;
        }

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String AvatarUrl) {
            this.AvatarUrl = AvatarUrl;
        }
    }
}
