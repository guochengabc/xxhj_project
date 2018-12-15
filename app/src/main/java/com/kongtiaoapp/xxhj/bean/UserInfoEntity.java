package com.kongtiaoapp.xxhj.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Luoye on 2016-7-22.
 * 说明: 用于存放用户信息的表
 */
@Table(name = "UserInfoEntity")
public class UserInfoEntity {
    private static UserInfoEntity userInfoBean = null;

    public static synchronized UserInfoEntity getUserInfoBean() {
        if (userInfoBean == null) {
            userInfoBean = new UserInfoEntity();
        }
        return userInfoBean;
    }

    public static void setUserInfoBean(UserInfoEntity userInfoBean) {
        UserInfoEntity.userInfoBean = userInfoBean;
    }

    @Column(name = "Phone")
    private String Phone;//电话号码

    @Column(name = "Credits")
    private String Credits;//积分

    @Column(name = "SuperiorId")
    private String SuperiorId;//上级ID编码

    @Column(name = "Location")
    private String Location;//地址

    @Column(name = "UserName")
    private String UserName;//用户姓名

    @Column(name = "UserId", isId = true)
    private String UserId;//用户ID号

    @Column(name = "City")
    private String City;//城市

    @Column(name = "OnLineTime")
    private String OnLineTime;//在线时长

    @Column(name = "Email")
    private String Email;//电子邮件

    @Column(name = "Interest")
    private String Interest;//爱好

    @Column(name = "OrgId")
    private String OrgId;

    @Column(name = "Level")
    private String Level;//等级

    @Column(name = "School")
    private String School;//学校

    @Column(name = "Avatar")
    private String Avatar;//头像

    @Column(name = "Gender")
    private String Gender;//性别
    @Column(name = "Gender")
    private String OrgName;//工作单位

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

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
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
