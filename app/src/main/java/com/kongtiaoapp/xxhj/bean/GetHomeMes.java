package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-9.
 * 说明:
 */
public class GetHomeMes extends RBResponse {

    /**
     * Category : D
     * NewMessage : 测试不行删申请加好友
     * Counts : 2
     */

    private List<HomeMessage> resobj;

    public List<HomeMessage> getResobj() {
        return resobj;
    }

    public void setResobj(List<HomeMessage> resobj) {
        this.resobj = resobj;
    }

    public static class HomeMessage {
        private String Category;
        private String NewMessage;
        private String Counts;
        private String SendTime;

        public String getSendTime() {
            return SendTime;
        }

        public void setSendTime(String sendTime) {
            SendTime = sendTime;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getNewMessage() {
            return NewMessage;
        }

        public void setNewMessage(String NewMessage) {
            this.NewMessage = NewMessage;
        }

        public String getCounts() {
            return Counts;
        }

        public void setCounts(String Counts) {
            this.Counts = Counts;
        }
    }
}
