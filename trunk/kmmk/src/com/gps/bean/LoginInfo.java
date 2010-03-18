package com.gps.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gps.orm.Organization;

public class LoginInfo {
	// google map
	public final static Integer GOOGLE_MAP = 0;
	// google map cn
	public final static Integer GOOGLE_MAP_CN = 1;
	// mapabc
	public final static Integer MAPABC = 2;
	// map type
	public static Map<Integer, String> mapTypes = new HashMap<Integer, String>();
	static {
		mapTypes.put(GOOGLE_MAP, "Google全球");
		mapTypes.put(GOOGLE_MAP_CN, "Google中国");
		mapTypes.put(MAPABC, "MapABC");
	}

	public Date loginTime;
	public Date lastLoginTime;

	private int userId;
	private int organizationId;

	private boolean isNewUI = false;
	private boolean isTz = false;

	private String passwd;
	private String realName;
	private int mapType;

	private String skin;
	private String loginName;

	public boolean isNewUI() {
		return isNewUI;
	}

	public void setNewUI(boolean isNewUI) {
		this.isNewUI = isNewUI;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	private Set<Integer> roles;

	public LoginInfo() {
		roles = new HashSet<Integer>();
	}

	public Set<Integer> getRoles() {
		return roles;
	}

	public void setRoles(Set<Integer> roles) {
		this.roles = roles;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	private void clear() {
	}

	public int getMapType() {
		return mapType;
	}

	public void setMapType(int mapType) {
		this.mapType = mapType;
	}

	public boolean isTz() {
		return isTz;
	}

	public void setTz(boolean isTz) {
		this.isTz = isTz;
	}
}
