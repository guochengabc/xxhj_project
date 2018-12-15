package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-10-14.
 * 说明:
 */
public class GetFriendInfo extends RBResponse {

    /**
     * Phone : 13601010203
     * Credits : 0
     * Location : 北京市
     * UserName : B级公司总管理
     * OrgName : 万达
     * UserId : d6408f41ec6b43889c4036622b8f995c
     * City : 北京市
     * AvatarUrl : image/avatar/d6408f41ec6b43889c4036622b8f995c.jpg
     * OnLineTime : 100
     * Email : 13601010203@qq.com
     * Interest : 互联网
     * OrgId : 8b1dafc0294748588a4ebc65f24bc9ba
     * Gender :
     * School : 海贼大学
     */

    private FriendInfo resobj;

    public FriendInfo getResobj() {
        return resobj;
    }

    public void setResobj(FriendInfo resobj) {
        this.resobj = resobj;
    }

    public static class FriendInfo {
        private String Phone;
        private String Credits;
        private String Location;
        private String UserName;
        private String OrgName;
        private String UserId;
        private String City;
        private String AvatarUrl;
        private String OnLineTime;
        private String Email;
        private String Interest;
        private String OrgId;
        private String Gender;
        private String School;
        private String IsShield;
        private String IsFriend;

        public String getIsShield() {
            return IsShield;
        }

        public void setIsShield(String isShield) {
            IsShield = isShield;
        }

        public String getIsFriend() {
            return IsFriend;
        }

        public void setIsFriend(String isFriend) {
            IsFriend = isFriend;
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

        public String getOrgName() {
            return OrgName;
        }

        public void setOrgName(String OrgName) {
            this.OrgName = OrgName;
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

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String AvatarUrl) {
            this.AvatarUrl = AvatarUrl;
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

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public String getSchool() {
            return School;
        }

        public void setSchool(String School) {
            this.School = School;
        }
    }
}
