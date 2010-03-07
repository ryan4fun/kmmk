package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	private int roleId;
	private String roleName;
	private String description;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Role() {
	}

	public Role(int roleId) {
		this.roleId = roleId;
	}

	public Role(int roleId, String roleName, String description,
			Set<UserRole> userRoles) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.userRoles = userRoles;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
