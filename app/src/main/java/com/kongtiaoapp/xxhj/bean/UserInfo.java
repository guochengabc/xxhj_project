package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;

/**
 * Created by Luoye on 2016-7-22.
 * 说明:
 */
public class UserInfo extends RBResponse implements Serializable {

    /**
     * Phone : 13601010202
     * Credits : 0
     * SuperiorId :
     * Location : 北京市
     * UserName : 李四
     * UserId : fa642bf648234d7791db8a9236856ba2
     * City : 北京市
     * OnLineTime : 10
     * Email : 13601010202@139.com
     * Interest : 互联网
     * OrgId : 8b1dafc0294748588a4ebc65f24bc9ba
     * Level : 0
     * School : 北京建筑大学
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean implements Serializable {
        private String Phone;//电话号码
        private String Credits;//积分
        private String SuperiorId;//上级ID编码
        private String Location;//地址
        private String UserName;//用户姓名
        private String UserId;//用户ID号
        private String City;//城市
        private String OnLineTime;//在线时长
        private String Email;//电子邮件
        private String Interest;//爱好
        private String OrgId;
        private String Level;//等级
        private String School;//学校
        private String Avatar;//头像
        private String AvatarUrl;//头像地址
        private String Gender;//性别
        private String MyCollection;//我的收藏
        private String MyPublish;//我的动态
        private String OrgName;//工作单位地址

        public String getOrgName() {
            return OrgName;
        }

        public void setOrgName(String orgName) {
            OrgName = orgName;
        }

        public String getMyCollection() {
            return MyCollection;
        }

        public void setMyCollection(String myCollection) {
            MyCollection = myCollection;
        }

        public String getMyPublish() {
            return MyPublish;
        }

        public void setMyPublish(String myPublish) {
            MyPublish = myPublish;
        }

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            AvatarUrl = avatarUrl;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String avatar) {
            Avatar = avatar;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getCredits() {
            return Credits;
        }

        public void setCredits(String Credits) {
            this.Credits = Credits;
        }

        public String getSuperiorId() {
            return SuperiorId;
        }

        public void setSuperiorId(String SuperiorId) {
            this.SuperiorId = SuperiorId;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
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

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getOnLineTime() {
            return OnLineTime;
        }

        public void setOnLineTime(String OnLineTime) {
            this.OnLineTime = OnLineTime;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getInterest() {
            return Interest;
        }

        public void setInterest(String Interest) {
            this.Interest = Interest;
        }

        public String getOrgId() {
            return OrgId;
        }

        public void setOrgId(String OrgId) {
            this.OrgId = OrgId;
        }

        public String getLevel() {
            return Level;
        }

        public void setLevel(String Level) {
            this.Level = Level;
        }

        public String getSchool() {
            return School;
        }

        public void setSchool(String School) {
            this.School = School;
        }
    }
}
