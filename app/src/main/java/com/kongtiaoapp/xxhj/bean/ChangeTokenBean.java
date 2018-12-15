package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2016/12/28.
 */
public class ChangeTokenBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Token":"f32ac0496e8e4edda63871e984118606"}
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
         * Token : f32ac0496e8e4edda63871e984118606
         */

        private String Token;

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }
    }
}
