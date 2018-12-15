package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;

/**
 * @author J
 *
 */
public class SortModel implements Serializable {

	private String name;
	private String sortLetters;
	private String phone;
	private String levelName;//管理员权限
	private String level;//管理员等级
	private String userId;
	public SortModel(String name, String sortLetters, boolean isChecked,
                     String iconUrl, int sex,String phone,String levelName,
					 String level,String userId) {
		super();
		this.name = name;
		this.sortLetters = sortLetters;
		this.isChecked = isChecked;
		this.iconUrl = iconUrl;
		this.sex = sex;
		this.phone=phone;
		this.levelName=levelName;
		this.level=level;
		this.userId=userId;
	}

	public SortModel() {
		super();
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	private boolean isChecked;
	private String iconUrl;
	private int sex; // 0 男 1 女

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
