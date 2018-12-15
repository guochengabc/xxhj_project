package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-19.
 * 说明:
 */
public class ChatMsg extends RBResponse {

    /**
     * ChatMessageId : 7821c0020fd2400c80b812dac5b49493
     * Message : 你好
     * CreateTime : 1476775914
     * UserName : 赵六
     * UserId : 66812464ef53415080ce6156bcb4cd30
     * AvatarUrl : images/avatar/ede00c02caf64c9dbc065889a5fa285c.jpg
     */

    private List<ChatMessage> resobj;

    public List<ChatMessage> getResobj() {
        return resobj;
    }

    public void setResobj(List<ChatMessage> resobj) {
        this.resobj = resobj;
    }

    public class ChatMessage {
        private String ChatMessageId;
        private String Message;
        private String CreateTime;
        private String UserName;
        private String UserId;
        private String AvatarUrl;

        public String getChatMessageId() {
            return ChatMessageId;
        }

        public void setChatMessageId(String ChatMessageId) {
            this.ChatMessageId = ChatMessageId;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
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
    }
}
