package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

	private int userId;
	private Organization organization;
	private String loginName;
	private String passwd;
	private String realName;
	private String description;
	private Date registerDate;
	private Date lastLoginDate;
	private String lastLoginIp;
	private Short userState;
	private String tel;
	private Set<Gpsfee> gpsfees = new HashSet<Gpsfee>(0);
	private Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
	private Set<Driver> drivers = new HashSet<Driver>(0);
	private Set<Escorter> escorters = new HashSet<Escorter>(0);
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Users() {
	}

	public Users(int userId) {
		this.userId = userId;
	}

	public Users(int userId, Organization organization, String loginName,
			String passwd, String realName, String description,
			Date registerDate, Date lastLoginDate, String lastLoginIp,
			Short userState, String tel, Set<Gpsfee> gpsfees,
			Set<Vehicle> vehicles, Set<Driver> drivers,
			Set<Escorter> escorters, Set<Task> tasks, Set<UserRole> userRoles) {
		this.userId = userId;
		this.organization = organization;
		this.loginName = loginName;
		this.passwd = passwd;
		this.realName = realName;
		this.description = description;
		this.registerDate = registerDate;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginIp = lastLoginIp;
		this.userState = userState;
		this.tel = tel;
		this.gpsfees = gpsfees;
		this.vehicles = vehicles;
		this.drivers = drivers;
		this.escorters = escorters;
		this.tasks = tasks;
		this.userRoles = userRoles;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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

	public Set<Gpsfee> getGpsfees() {
		return this.gpsfees;
	}

	public void setGpsfees(Set<Gpsfee> gpsfees) {
		this.gpsfees = gpsfees;
	}

	public Set<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Set<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}

	public Set<Escorter> getEscorters() {
		return this.escorters;
	}

	public void setEscorters(Set<Escorter> escorters) {
		this.escorters = escorters;
	}

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
