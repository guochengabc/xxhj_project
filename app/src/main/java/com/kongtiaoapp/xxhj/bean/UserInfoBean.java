package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/7/5.
 */

public class UserInfoBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"AvatarUrl":"images/avatar/ae0f9a93723d44c2978275d9204d4a23.jpg","Level":"B","LevelName":"企业创建人","Phone":"13521489312","UserId":"2a2ff5e9fe0d4dada0b9b2c9aa6a1e9b","UserName":"灬达"},{"Level":"C","LevelName":"一般操作员","Phone":"13691048387","UserId":"4e5f3983c55e45e5b2bb87969a64826e","UserName":"不知道"},{"AvatarUrl":"images/avatar/7bf6ed240a774e2ca5adce0fbfcb31fd.jpg","Level":"B","LevelName":"企业管理员","Phone":"18515292122","UserId":"bc223bf47c644aa1b8eacbb602ea83bf","UserName":"郭成"}]
     */

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * AvatarUrl : images/avatar/ae0f9a93723d44c2978275d9204d4a23.jpg
         * Level : B
         * LevelName : 企业创建人
         * Phone : 13521489312
         * UserId : 2a2ff5e9fe0d4dada0b9b2c9aa6a1e9b
         * UserName : 灬达
         */

        private String AvatarUrl;
        private String Level;
        private String LevelName;
        private String Phone;
        private String UserId;
        private String UserName;

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String AvatarUrl) {
            this.AvatarUrl = AvatarUrl;
        }

        public String getLevel() {
            return Level;
        }

        public void setLevel(String Level) {
            this.Level = Level;
        }

        public String getLevelName() {
            return LevelName;
        }

        public void setLevelName(String LevelName) {
            this.LevelName = LevelName;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }
    }
}
