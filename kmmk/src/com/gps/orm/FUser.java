package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * FUser generated by hbm2java
 */
public class FUser implements java.io.Serializable {

	private int userId;
	private String loginName;
	private String passwd;
	private String realName;
	private String description;
	private Date registerDate;
	private Date lastLoginDate;
	private String lastLoginIp;
	private Short userState;
	private String tel;

	public FUser() {
	}

	public FUser(int userId) {
		this.userId = userId;
	}

	public FUser(int userId, String loginName, String passwd, String realName,
			String description, Date registerDate, Date lastLoginDate,
			String lastLoginIp, Short userState, String tel) {
		this.userId = userId;
		this.loginName = loginName;
		this.passwd = passwd;
		this.realName = realName;
		this.description = description;
		this.registerDate = registerDate;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginIp = lastLoginIp;
		this.userState = userState;
		this.tel = tel;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Short getUserState() {
		return this.userState;
	}

	public void setUserState(Short userState) {
		this.userState = userState;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
