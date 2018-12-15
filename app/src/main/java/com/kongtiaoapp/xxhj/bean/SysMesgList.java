package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-9.
 * 说明:
 */
public class SysMesgList extends RBResponse {

    /**
     * Message : 这是一条诊断信息
     * CreateTime : 1473322272
     * SystemMessageId : 5ef9b4e4ace24ab4944f7314a4a99dd7
     */

    private List<SysMesg> resobj;

    public List<SysMesg> getResobj() {
        return resobj;
    }

    public void setResobj(List<SysMesg> resobj) {
        this.resobj = resobj;
    }

    public static class SysMesg {
        private String Message;
        private String CreateTime;
        private String SystemMessageId;

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

        public String getSystemMessageId() {
            return SystemMessageId;
        }

        public void setSystemMessageId(String SystemMessageId) {
            this.SystemMessageId = SystemMessageId;
        }
    }
}
