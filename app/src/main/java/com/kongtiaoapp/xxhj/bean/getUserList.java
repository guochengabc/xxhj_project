package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luoye on 2016-9-12.
 * 说明:
 */
public class getUserList extends RBResponse implements Serializable{

    /**
     * Phone : 13601010203
     * UserName : 王五
     * UserId : d6408f41ec6b43889c4036622b8f995c
     */

    private List<UserList> resobj;

    public List<UserList> getResobj() {
        return resobj;
    }

    public void setResobj(List<UserList> resobj) {
        this.resobj = resobj;
    }

    public static class UserList implements Serializable{
        private String Phone;
        private String UserName;
        private String UserId;

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
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
    }
}
