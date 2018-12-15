package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-6-13.
 */
public class LoginBean extends RBResponse {


    /**
     * code : 40000
     * resobj : {"LoginType":"BB","Roles":["CB","BB"],"Token":"35f2a3c3b14d406f950b48f67b88c309","UserId":"bc223bf47c644aa1b8eacbb602ea83bf"}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * LoginType : BB
         * Roles : ["CB","BB"]
         * Token : 35f2a3c3b14d406f950b48f67b88c309
         * UserId : bc223bf47c644aa1b8eacbb602ea83bf
         */

        private String LoginType;
        private String Token;
        private String UserId;
        private List<String> Roles;

        public String getLoginType() {
            return LoginType;
        }

        public void setLoginType(String LoginType) {
            this.LoginType = LoginType;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public List<String> getRoles() {
            return Roles;
        }

        public void setRoles(List<String> Roles) {
            this.Roles = Roles;
        }
    }
}
